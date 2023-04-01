package br.com.itads.miniauth.util;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 */
public class ThreadUtils {

  /**
   * Executa um sleep da Thread por tempo randomico Muito util para reduzir concorrência.
   */
  public static void randomSleep() {
    try {
      Thread.sleep((long) (Math.random() * 1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Executa um sleep por um segundo.
   * Usado par evidenciar uma lentidão de transacao e 
   * cair em cenários de concorrência.
   */
  public static void sleepFor1Sec() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
