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
 * Aspecto utilizado para realizar log de troca de mensagens entre objetos
 * de forma a reduzir o espalhamento de código 
 * 
 * @author marioromeu
 * @email  mario.romeu@gmail.com
 *
 */
@Aspect
@Component
@Async
public class LoggingAspect {

  /**
   * 
   */
  private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

  @Around("@annotation(br.com.itads.miniauth.aspect.interfaces.LoggingMyAuth)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

    log.debug("Berfore " + joinPoint.getSignature().getName());
    
    Object proceed = joinPoint.proceed();

    if (proceed instanceof String) {
      log.debug("Param : " + proceed);
    }

    log.debug("Berfore " + joinPoint.getSignature().getName());

    return proceed;

  }

}
