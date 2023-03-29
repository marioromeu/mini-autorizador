package br.com.itads.miniauth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.com.itads.miniauth.dto.CardDTO;
import br.com.itads.miniauth.exception.CardAlreadyExistsException;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.exception.InvalidCardFormatException;
import br.com.itads.miniauth.exception.NoRefundsException;
import br.com.itads.miniauth.model.Card;
import br.com.itads.miniauth.repository.CardRepository;
import br.com.itads.miniauth.services.interfaces.CardService;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@Service
public class CardServiceImpl implements CardService {

  /**
   * 
   */
  @Autowired
  private CardRepository repository;

  /**
   * @throws InvalidCardFormatException 
   * 
   */
  public void createNewCard(CardDTO cardDTO) throws CardAlreadyExistsException, InvalidCardFormatException {

    isValidCardFormat(cardDTO.getNumeroCartao());
    
    Card card = Card.builder().funds(500d).number(cardDTO.getNumeroCartao())
        .password(cardDTO.getSenha()).build();

    try {
      repository.save(card);

    } catch (DataIntegrityViolationException e) {
      throw new CardAlreadyExistsException();

    }

  }

  /**
   * @throws InvalidCardFormatException 
   * 
   */
  public Card findCardByNumber(String cardNumber) throws CardNotFoundException, InvalidCardFormatException {
    
    Card card = null;

    isValidCardFormat(cardNumber);
    
    try {
      card = repository.findCardByNumber(cardNumber);
      card.getId();// TODO Melhorar

    } catch (NullPointerException e) {
      throw new CardNotFoundException();

    }

    return card;

  }

  /**
   * @return
   * @throws InvalidCardFormatException
   * 
   */
  private Boolean isValidCardFormat(String cardNumber) throws InvalidCardFormatException {

    Boolean bool = cardNumber.matches("^\\d+$");

    if (!bool) {
      throw new InvalidCardFormatException();
    }

    return bool;

  }

  /**
   * 
   */
  @Override
  public void debitValue(Card card)
      throws NoRefundsException, CardAlreadyExistsException, InvalidCardFormatException {

    isValidCardFormat(card.getNumber());

    try {
      repository.save(card);

    } catch (DataIntegrityViolationException e) {
      throw new CardAlreadyExistsException();

    }
    
  }

}
