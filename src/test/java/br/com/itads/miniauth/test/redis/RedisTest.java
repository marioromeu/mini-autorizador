package br.com.itads.miniauth.test.redis;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.itads.miniauth.aspect.RedisAspect;
import br.com.itads.miniauth.cache.RedisCacheRepository;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RedisAspect.class})
class RedisTest {

  /**
   * 
   */
  @Autowired
  RedisAspect redisAspect;

  /**
   * 
   */
  @MockBean
  RedisCacheRepository redisRepository;

  /**
   * 
   */
  @MockBean
  RedisTemplate<String, String> redisTemplate;

  /**
   * 
   */
  @Test
  void block() {
    String key = "598745154569548743";

    when(redisRepository.set(key)).thenReturn(key);

    redisAspect.block(key);

    verify(redisRepository).set(key);
  }

  /**
   * 
   */
  @Test
  void get() {
    String key = "59874515456954874";

    when(redisRepository.get(key)).thenReturn(key);

    redisAspect.get(key);

    verify(redisRepository).get(key);
  }

  /**
   * 
   */
  @Test
  void unlock() {
    String key = "59874515456954875";

    when(redisTemplate.boundValueOps(any())).thenReturn(any());

    redisAspect.unlock(key);

    verify(redisRepository).remove(key);
  }

}
