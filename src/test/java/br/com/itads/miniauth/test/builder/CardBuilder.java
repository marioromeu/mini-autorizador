package br.com.itads.miniauth.test.builder;

import br.com.itads.miniauth.dto.CardDTO;
import br.com.itads.miniauth.model.Card;

/**
 * 
 * @author marioromeu
 * @email  mario.romeu@gmail.com
 *
 */
public class CardBuilder extends GenericBuilder {

  /**
   * 
   * @return
   */
  public static final CardDTO buildCardDTOBasedOnCard(Card card) {
    return CardDTO.builder()
        .numeroCartao(card.getNumber())
        .senha(card.getPassword())
        .build();
  }  
  
  /**
   * 
   * @return
   */
  public static final Card buildNewCardWithNumber0() {
    return Card.builder()
        .id(1l)
        .funds(500d)
        .number("0000000000000000")
        .password(encryptedDefaultPassword)
        .build();
  }
  
  /**
   * 
   * @return
   */
  public static final Card buildNewInvalidCard() {
    
    Double d = Math.random()* 10000;
    Long idLong = d.longValue();
    
    Card card = Card.builder()
        .id(idLong)
        .funds(500d)
        .number(defaultCardNumber+1)
        .password(encryptedDefaultPassword)
        .build();

    return card;
  }   

  /**
   * 
   * @return
   */
  public static final Card buildNewCardCleanPassword() {
    
    Double d = Math.random()* 10000;
    Long idLong = d.longValue();
    
    Card card = Card.builder()
        .id(idLong)
        .funds(500d)
        .number(defaultCardNumber)
        .password(defaultPassword)
        .build();

    return card;
  }   
  
  /**
   * 
   * @return
   */
  public static final Card buildNewCardLikeReadMe() {
    
    Double d = Math.random()* 10000;
    Long idLong = d.longValue();
    
    Card card = Card.builder()
        .id(idLong)
        .funds(500d)
        .number(defaultCardNumber)
        .password(encryptedDefaultPassword)
        .build();

    return card;
  }  
  
  /**
   * 
   * @return
   */
  public static final Card buildNewCardWithoutFunds() {
    
    Double d = Math.random()* 10000;
    Long idLong = d.longValue();
    
    Card card = Card.builder()
        .id(idLong)
        .funds(0d)
        .number(defaultCardNumber)
        .password(encryptedDefaultPassword)
        .build();

    return card;
  }   
  
}
