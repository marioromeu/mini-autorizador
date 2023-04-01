package br.com.itads.miniauth.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.itads.miniauth.cache.RedisCacheRepository;
import br.com.itads.miniauth.dto.TransactionDTO;
import br.com.itads.miniauth.util.ThreadUtils;

/**
 * 
 * Este aspecto foi criado para intercerptar as mensagens enviadas
 * ao objeto responsável por processar a lógica de negócio das transações
 * da aplicação, de forma a previamente bloquear o cartão em um cache redis
 * visando impedir que duas transações paralelamente debitem do cartão.
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@Component
@Aspect
public class RedisAspect {

  /**
   * 
   */
  @Autowired
  RedisCacheRepository redisCacheRepository;

  /**
   * 
   */
  private static final Logger log = LoggerFactory.getLogger(RedisAspect.class);

  /**
   * 
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("@annotation(RedisLockTransaction)")
  public Object transactionLocker(ProceedingJoinPoint joinPoint) throws Throwable {

    TransactionDTO dto = (TransactionDTO) joinPoint.getArgs()[0];
    
    lockAndLoad(dto);
    
    Object proceed = joinPoint.proceed();

    unlock(dto.getNumeroCartao());

    return proceed;

  }

  /**
   * Realiza o lock de um cartão no redis, para que
   * duas transações não tentem processar o mesmo cartão
   * e debitar juntar, causando um possível erro no débito
   * do valor devido.
   * 
   * @param dto
   */
  private void lockAndLoad(TransactionDTO dto) {
    
    String cardNumberLocked = get(dto.getNumeroCartao());    
    
    if (cardNumberLocked == null) {

      log.info("Locking " + dto.getNumeroCartao() + " card.");

      block(dto.getNumeroCartao());

      log.info(dto.getNumeroCartao() + " LOCKED ");

    } else {

      ThreadUtils.randomSleep();

      log.info("A Transaction for " + dto.getNumeroCartao() + " card is locked. Retrying...");

      lockAndLoad(dto);

    }

  }

  /**
   * 
   * @param key
   * @return
   */
  public String block(String key) {
    return redisCacheRepository.set(key);
  }

  /**
   * 
   * @param key
   * @return
   */
  public String get(String key) {
    return redisCacheRepository.get(key);
  }

  /**
   * 
   * @param key
   */
  public void unlock(String key) {
    redisCacheRepository.remove(key);
  }

}
