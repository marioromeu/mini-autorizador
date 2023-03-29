package br.com.itads.miniauth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.itads.miniauth.dto.TransactionDTO;
import br.com.itads.miniauth.exception.CardNotFoundException;
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
   * 
   */  
  public void createNewTransaction(TransactionDTO dto) throws TransactionNotAllowedException {

    try {

      Card card = cardService.findCardByNumber(dto.getNumeroCartao());
      
      SecurityUtils.isPasswordValid(card.getPassword(), dto.getSenha());
     
      //TODO colocar a lógica do controle da transação
      throw new NoRefundsException();
      
    }catch (PasswordInvalidException e1) {
      throw new TransactionNotAllowedException();          
      
    } catch (CardNotFoundException e2) {
      throw new TransactionNotAllowedException();
      
    } catch (NoRefundsException e3) {
      throw new TransactionNotAllowedException();      

    }
    
  }
  
}
