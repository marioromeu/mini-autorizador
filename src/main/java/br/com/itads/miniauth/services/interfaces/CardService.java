package br.com.itads.miniauth.services.interfaces;

import br.com.itads.miniauth.dto.CardDTO;
import br.com.itads.miniauth.exception.CardAlreadyExists;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.model.Card;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
public interface CardService {

  /**
   * 
   * @param cardDTO
   * @throws CardAlreadyExists
   */
  void createNewCard(CardDTO cardDTO) throws CardAlreadyExists;
  
  /**
   * 
   * @param numeroCartao
   * @return
   */
  Card findCardByNumber(String cardNumber) throws CardNotFoundException;
  
}
