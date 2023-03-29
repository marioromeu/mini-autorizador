package br.com.itads.miniauth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.itads.miniauth.dto.CardDTO;
import br.com.itads.miniauth.exception.CardAlreadyExists;
import br.com.itads.miniauth.exception.CardNotFoundException;
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
   * 
   */
  public void createNewCard(CardDTO cardDTO) throws CardAlreadyExists {

    Card card = Card.builder()
        .funds(500d)        
        .number(cardDTO.getNumeroCartao())        
        .password(cardDTO.getSenha())
        .build();
    
    repository.save(card);

  }

  /**
   * 
   */
  public Card findCardByNumber(String cardNumber) throws CardNotFoundException {
    return repository.findCardByNumber(cardNumber);
  }

}
