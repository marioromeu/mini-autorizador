package br.com.itads.miniauth.services.impl;

import org.springframework.stereotype.Service;
import br.com.itads.miniauth.dto.TransactionDTO;
import br.com.itads.miniauth.exception.TransactionNotAllowedException;
import br.com.itads.miniauth.services.interfaces.TransactionService;

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
  public void createNewTransaction(TransactionDTO body) throws TransactionNotAllowedException {

  }
  
}
