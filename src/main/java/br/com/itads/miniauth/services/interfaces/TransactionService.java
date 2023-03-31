package br.com.itads.miniauth.services.interfaces;

import br.com.itads.miniauth.dto.TransactionDTO;
import br.com.itads.miniauth.exception.CardAlreadyExistsException;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.exception.InvalidCardFormatException;
import br.com.itads.miniauth.exception.NoRefundsException;
import br.com.itads.miniauth.exception.PasswordInvalidException;
import br.com.itads.miniauth.exception.TransactionNotAllowedException;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
public interface TransactionService {


  /**
   * 
   * @param cardDTO
   * @return
   * @throws CardAlreadyExistsException 
   * @throws InvalidCardFormatException 
   * @throws CardNotFoundException 
   * @throws PasswordInvalidException 
   * @throws NoRefundsException 
   * @throws TransactionNotAllowedException 
   */
  void createNewTransaction(TransactionDTO body) throws CardNotFoundException, PasswordInvalidException, NoRefundsException, TransactionNotAllowedException;
  
}
