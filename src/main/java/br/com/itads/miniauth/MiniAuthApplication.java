package br.com.itads.miniauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author marioromeu
 * @email mario.romeu@gmail.com
 */
@SpringBootApplication
@ComponentScan(basePackages = {"br.com.itads.miniauth"})
public class MiniAuthApplication {

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {

    /**
     * 
     */
    SpringApplication.run(MiniAuthApplication.class, args);

  }

}
