package br.com.itads.miniauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 * @author marioromeu
 * @email  mario.romeu@gmail.com
 *
 */
@Configuration
public class RedisConfiguration {

  /**
   * 
   */
  @Value("${redis.host}")
  private String host;

  /**
   * 
   */
  @Value("${redis.port}")
  private int port;

  /**
   * 
   * @return
   */
  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
    return new JedisConnectionFactory(redisStandaloneConfiguration);
  }

  /**
   * 
   * @param redisConnectionFactory
   * @return
   */
  @Bean(value = "redisTemplate")
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    return redisTemplate;
  }

}
