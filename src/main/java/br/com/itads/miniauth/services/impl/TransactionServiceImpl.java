package br.com.itads.miniauth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.itads.miniauth.dto.TransactionDTO;
import br.com.itads.miniauth.exception.CardAlreadyExistsException;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.exception.InvalidCardFormatException;
import br.com.itads.miniauth.exception.NoRefundsException;
import br.com.itads.miniauth.exception.PasswordInvalidException;
import br.com.itads.miniauth.exception.TransactionNotAllowedException;
import br.com.itads.miniauth.model.Card;
import br.com.itads.miniauth.services.interfaces.CardService;
import br.com.itads.miniauth.services.interfaces.TransactionService;
import br.com.itads.miniauth.util.SecurityUtils;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

  /**
   * 
   */
  @Autowired
  private CardService cardService;

  /**
   * @throws CardAlreadyExistsException 
   * 
   */
  public void createNewTransaction(TransactionDTO dto) throws TransactionNotAllowedException, CardAlreadyExistsException {

    try {

      Card card = cardService.findCardByNumber(dto.getNumeroCartao());

      SecurityUtils.isPasswordValid(card.getPassword(), dto.getSenhaCartao());

      processTransaction(card, dto.getValor());

    } catch (PasswordInvalidException | CardNotFoundException | NoRefundsException | InvalidCardFormatException e) {
      throw new TransactionNotAllowedException();
    }

  }

  /**
   * @throws InvalidCardFormatException 
   * @throws CardAlreadyExistsException 
   * 
   */
  private synchronized void processTransaction(Card card, Double valueOfTransaction)
      throws NoRefundsException, CardAlreadyExistsException, InvalidCardFormatException {

    card.debit(valueOfTransaction);
    
    cardService.debitValue(card);

  }

}
