package br.com.itads.miniauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@SpringBootApplication
@EnableJpaRepositories("br.com.itads.*")
@ComponentScan(basePackages = { "br.com.itads.*" })
@EntityScan("br.com.itads.*")   
//@EnableCaching
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
