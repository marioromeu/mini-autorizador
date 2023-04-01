package br.com.itads.miniauth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.itads.miniauth.aspect.RedisLockTransaction;
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
import br.com.itads.miniauth.util.ThreadUtils;

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
  @RedisLockTransaction  
  public void processTransaction(TransactionDTO dto)
      throws CardNotFoundException, PasswordInvalidException, NoRefundsException, TransactionNotAllowedException {

    try {

      //Consulta o cartão
      Card card = cardService.findCardByNumber(dto.getNumeroCartao());

      //Criptografa a senha informada na api
      SecurityUtils.isPasswordValid(card.getPassword(), dto.getSenhaCartao());

      /**
       * Se há saldo...
       */
      if (card.getFunds() > dto.getValor()) {
        
        ProcessTransactionDTO processTransactionDTO =
            ProcessTransactionDTO.builder()
                              .card(card)
                              .valueOfTransaction(dto.getValor())
                              .build();
        
        Double valueOfTransaction = processTransactionDTO.getValueOfTransaction();
        
        card.debit(valueOfTransaction);

        Object lock = new Object();

        /**
         * sleep para ajudar a perceber o 
         * paralelismo e concorrencia de requisicoes 
         */
        ThreadUtils.sleepFor1Sec();
        
        //debita no banco de dados
        synchronized (lock) {      
          cardService.debitValue(card);  
        }

        /**
         * Se não há saldo
         */
      } else {
        throw new NoRefundsException();
      }

    } catch (InvalidCardFormatException e) {
      throw new TransactionNotAllowedException();

    } catch (CardAlreadyExistsException e) {
      throw new TransactionNotAllowedException();
    }

  }

}
