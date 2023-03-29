package br.com.itads.miniauth.services.interfaces;

import br.com.itads.miniauth.dto.CardDTO;
import br.com.itads.miniauth.exception.CardAlreadyExistsException;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.exception.InvalidCardFormatException;
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
   * @throws InvalidCardFormatException 
   * @throws CardAlreadyExists
   */
  void createNewCard(CardDTO cardDTO) throws CardAlreadyExistsException, InvalidCardFormatException;
  
  /**
   * 
   * @param numeroCartao
   * @return
   * @throws InvalidCardFormatException 
   */
  Card findCardByNumber(String cardNumber) throws CardNotFoundException, InvalidCardFormatException;
  
}
