package br.com.itads.miniauth.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


/**
 * 
 * Aspecto criado para calcular o tempo de execução de um método
 * assinado com @LogExecutionTime.
 * 
 * O resultado eh logado em arquivo, usando mecanismo comum de log
 * permitindo consultar a performance da aplicação em cenários de 
 * alto volume e carga
 * 
 * @author marioromeu
 * @email  mario.romeu@gmail.com
 *
 */
@Aspect
@Component
@Async
public class PerformanceAspect {

  /**
   * 
   */
  private static final Logger log = LoggerFactory.getLogger(PerformanceAspect.class);

  /**
   * 
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("@annotation(br.com.itads.miniauth.aspect.interfaces.LogExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

    long start = System.currentTimeMillis();
    
    Object proceed = joinPoint.proceed();

    long executionTime = System.currentTimeMillis() - start;

    log.info(joinPoint.getSignature().getName() + " executed in " + executionTime + "ms");

    return proceed;

  }

}
