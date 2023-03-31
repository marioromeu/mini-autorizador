package br.com.itads.miniauth.test.redis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.exception.InvalidCardFormatException;
import br.com.itads.miniauth.model.Card;
import br.com.itads.miniauth.repository.CardRepository;
import br.com.itads.miniauth.services.impl.CardServiceImpl;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@Import({CacheConfig.class, Card.class})
@ExtendWith(SpringExtension.class)
@EnableCaching
@ImportAutoConfiguration(classes = {CacheAutoConfiguration.class, RedisAutoConfiguration.class})
public class TransactionCachingIntegrationTest {

  /**
   * 
   */
  @MockBean
  private CardRepository cardRepository;

  /**
   * 
   */
  @Autowired
  private CardServiceImpl cardService;

  /**
   * 
   */
  @Autowired
  private CacheManager cacheManager;
  
  /**
   * 
   */
  @Autowired
  private ApplicationContext ctx;

  /**
   * @throws InvalidCardFormatException 
   * @throws CardNotFoundException 
   * 
   */
  //@Test
  void testGivenRedisCaching_whenFindItemById_thenItemReturnedFromCache() throws CardNotFoundException, InvalidCardFormatException {

    Card card = new Card(1l, 500d, "0000000000000000", "1234");
    
    when(cardRepository.findCardByNumber(card.getNumber())).thenReturn(card);
    
    Card cardMiss = cardService.findCardByNumber(card.getNumber().concat("?"));
    Card cardHit = cardService.findCardByNumber(card.getNumber());

    assertThat(cardHit).isEqualTo(card);
    assertThat(cardMiss).isEqualTo(card);

    verify(cardRepository, times(1)).findCardByNumber(card.getNumber());

    assertThat(cardFromCache(card)).isEqualTo(card);

  }

  /**
   * 
   * @return
   */
  private Card cardFromCache(Card card) {

    return (Card) cacheManager.getCache(card.getNumber());

  }
  
}
