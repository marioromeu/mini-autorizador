package br.com.itads.miniauth.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@Repository
public class RedisCacheRepository {

  /**
   * 
   */
  @Autowired
  RedisTemplate<String, String> template;

  /**
   * 
   * @param key
   * @return
   */
  public String get(String key) {
    String definitiveKey = buildKey(key);
    return template.boundValueOps(definitiveKey).get();
  }

  /**
   * 
   * @param key
   * @return
   */
  public String set(String key) {
    String definitiveKey = buildKey(key);
    template.boundValueOps(definitiveKey).set(key);
    return key;
  }

  /**
   * 
   * @param key
   * @return
   */
  public void remove(String key) {
    String definitiveKey = buildKey(key);
    template.boundValueOps(definitiveKey).getAndDelete();
  }

  /**
   * 
   * @param key
   * @return
   */
  private String buildKey(String key) {
    return "mini-auth:transaction:".concat(key);
  }

}
