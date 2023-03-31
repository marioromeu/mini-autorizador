package br.com.itads.miniauth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.itads.miniauth.aspect.LogExecutionTime;
import br.com.itads.miniauth.dto.ProcessTransactionDTO;
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
   * @throws InvalidCardFormatException
   * @throws CardNotFoundException
   * @throws PasswordInvalidException
   * @throws NoRefundsException
   * @throws TransactionNotAllowedException 
   * 
   */
  public void createNewTransaction(TransactionDTO dto)
      throws CardNotFoundException, PasswordInvalidException, NoRefundsException, TransactionNotAllowedException {

    try {

      Card card = cardService.findCardByNumber(dto.getNumeroCartao());

      SecurityUtils.isPasswordValid(card.getPassword(), dto.getSenhaCartao());

      /**
       * Se ha recurso...
       */
      if (card.getFunds() > dto.getValor()) {
        
        ProcessTransactionDTO processTransactionDTO =
            ProcessTransactionDTO.builder()
                              .card(card)
                              .valueOfTransaction(dto.getValor())
                              .build();

        processTransaction(processTransactionDTO);

      } else {
        throw new NoRefundsException();
      }

    } catch (InvalidCardFormatException e) {
      throw new TransactionNotAllowedException();

    } catch (CardAlreadyExistsException e) {
      throw new TransactionNotAllowedException();

    }

  }

  /**
   * @throws InvalidCardFormatException
   * @throws CardAlreadyExistsException
   * 
   */
  //@RedisLockTransaction
  @LogExecutionTime  
  public synchronized void processTransaction(ProcessTransactionDTO dto)
      throws NoRefundsException, CardAlreadyExistsException, InvalidCardFormatException {
    System.out.println("Inicio-----------");
    Card card = dto.getCard();
    
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    Double valueOfTransaction = dto.getValueOfTransaction();
    
    card.debit(valueOfTransaction);

    cardService.debitValue(card);
    System.out.println("-----------FIM");
  }

}
