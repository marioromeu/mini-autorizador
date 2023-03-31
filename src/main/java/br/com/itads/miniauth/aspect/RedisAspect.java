package br.com.itads.miniauth.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.itads.miniauth.dto.ProcessTransactionDTO;

/**
 * 
 * @author marioromeu
 * @email  mario.romeu@gmail.com
 *
 */
@Aspect
public class RedisAspect {

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
  //@Around(value="@annotation(br.com.itads.miniauth.aspect.RedisLockTransaction)")  
  @Pointcut("@annotation(br.com.itads.miniauth.aspect.RedisLockTransaction)")
  public void timerPointcut() {
    System.out.println("aki");
  }  
  
  @Around("timerPointcut() ")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

    log.debug("Before " + joinPoint.getSignature().getName());
    
    Object proceed = joinPoint.proceed();

    if (proceed instanceof ProcessTransactionDTO) {

      //TODO colocar a logica do redis

    }

    log.debug("After " + joinPoint.getSignature().getName());

    return proceed;

  }

}
