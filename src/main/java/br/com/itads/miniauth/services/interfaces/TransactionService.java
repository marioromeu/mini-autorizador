package br.com.itads.miniauth.services.interfaces;

import br.com.itads.miniauth.dto.TransactionDTO;
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
   */
  void createNewTransaction(TransactionDTO body) throws TransactionNotAllowedException;
  
}
