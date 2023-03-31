package br.com.itads.miniauth.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import br.com.itads.miniauth.controller.interfaces.TransactionControllerInterface;
import br.com.itads.miniauth.dto.TransactionDTO;
import br.com.itads.miniauth.enums.TransactionEnum;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.exception.NoRefundsException;
import br.com.itads.miniauth.exception.PasswordInvalidException;
import br.com.itads.miniauth.exception.TransactionNotAllowedException;
import br.com.itads.miniauth.services.interfaces.TransactionService;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@RestController
public class TransactionController implements TransactionControllerInterface {

  /**
   * 
   */
  @Autowired
  private TransactionService service;

  /**
   * 
   */
  public ResponseEntity<TransactionEnum> createNewTransaction(MultiValueMap<String, String> header, TransactionDTO body) {

    ResponseEntity<TransactionEnum> responseEntity;

    try {

      service.createNewTransaction(body);

      responseEntity = new ResponseEntity<TransactionEnum>(TransactionEnum.OK, HttpStatus.CREATED);

    } catch (CardNotFoundException e) {
      responseEntity = new ResponseEntity<TransactionEnum>(TransactionEnum.CARTAO_INEXISTENTE, HttpStatus.UNPROCESSABLE_ENTITY);
      
    } catch (PasswordInvalidException e) {
      responseEntity = new ResponseEntity<TransactionEnum>(TransactionEnum.SENHA_INVALIDA, HttpStatus.UNPROCESSABLE_ENTITY);
      
    } catch (NoRefundsException e) {
      responseEntity = new ResponseEntity<TransactionEnum>(TransactionEnum.SALDO_INSUFICIENTE, HttpStatus.UNPROCESSABLE_ENTITY);
      
    } catch (TransactionNotAllowedException e) {
      responseEntity = new ResponseEntity<TransactionEnum>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    return responseEntity;

  }

}
