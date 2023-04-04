package br.com.itads.miniauth.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
public class MethodLoggingAspect {

  /**
   * 
   */
  private static final Logger log = LoggerFactory.getLogger(MethodLoggingAspect.class); 
  
  /**
   * 
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Before("@annotation(MethodLogging)")
  public void logBefore(JoinPoint joinPoint) throws Throwable {    
    log.debug("Before " + joinPoint.getSignature().getName() + " - " + joinPoint.getArgs());
  }

  /**
   * 
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @After("@annotation(MethodLogging)")
  public void logAfter(JoinPoint joinPoint) throws Throwable {    
    log.debug("After " + joinPoint.getSignature().getName() + " - " + joinPoint.getArgs());
  }  
  
}
