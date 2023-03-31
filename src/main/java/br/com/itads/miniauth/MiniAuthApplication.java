package br.com.itads.miniauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author marioromeu
 * @email mario.romeu@gmail.com
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaRepositories("br.com.itads.miniauth.repository")
@EntityScan("br.com.itads.miniauth.model")
@ComponentScan(basePackages = {
    "br.com.itads.miniauth.aspect",
    "br.com.itads.miniauth.controller.*",
    "br.com.itads.miniauth.dto",
    "br.com.itads.miniauth.enums",
    "br.com.itads.miniauth.exception",
    "br.com.itads.miniauth.model",
    "br.com.itads.miniauth.repository",
    "br.com.itads.miniauth.responses",
    "br.com.itads.miniauth.services.*",
    "br.com.itads.miniauth.util",
})
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
