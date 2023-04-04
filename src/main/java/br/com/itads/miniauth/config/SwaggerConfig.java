package br.com.itads.miniauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * 
 * @author marioromeu
 * @email  mario.romeu@gmail.com
 *
 */
@Configuration
public class SwaggerConfig {

  /**
   * 
   * @return
   */
    @Bean
    public OpenAPI showAPI() {

        return new OpenAPI()
                .info(new Info().title("mini-autorizador")
                        .description("Mini Autorizador - Desafio VR Beneficios")
                        .version("0.0.1-SNAPSHOT"));
    }
}
