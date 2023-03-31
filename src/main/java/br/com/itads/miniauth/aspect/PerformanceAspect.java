package br.com.itads.miniauth.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
  @Around("@annotation(LogExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

    long start = System.currentTimeMillis();
    
    Object proceed = joinPoint.proceed();

    long executionTime = System.currentTimeMillis() - start;

    log.info(joinPoint.getSignature().getName() + " executed in " + executionTime + "ms");
    System.out.println(joinPoint.getSignature().getName() + " executed in " + executionTime + "ms");
    
    return proceed;

  }

}
