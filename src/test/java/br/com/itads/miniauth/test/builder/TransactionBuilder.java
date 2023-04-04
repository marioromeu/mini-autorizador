package br.com.itads.miniauth.test.builder;

import br.com.itads.miniauth.dto.TransactionDTO;

/**
 * 
 * @author marioromeu
 * @email  mario.romeu@gmail.com
 *
 */
public class TransactionBuilder extends GenericBuilder {

  
  /**
   * 
   * @return
   */
  public static final TransactionDTO buildNewTransactionDTOWithNumber0() {
    return TransactionDTO.builder()
        .valor(100d)
        .numeroCartao("0000000000000000")
        .senhaCartao(defaultPassword)
        .build();
  }
  
  /**
   * 
   * @return
   */
  public static final TransactionDTO buildNewTransactionDTOLikeReadMe() {
    
    TransactionDTO transactionDTO = TransactionDTO.builder()
        .valor(100d)
        .numeroCartao(defaultCardNumber)
        .senhaCartao(defaultPassword)        
        .build();

    return transactionDTO;
  }  
  
  /**
   * 
   * @return
   */
  public static final TransactionDTO buildNewTransactionDTOCardNotFound() {
    
    TransactionDTO transactionDTO = TransactionDTO.builder()
        .valor(100d)
        .numeroCartao("?????????")
        .senhaCartao(defaultPassword)        
        .build();

    return transactionDTO;
  }   
  
  /**
   * 
   * @return
   */
  public static final TransactionDTO buildNewTransactionWithCardWrongPasswd() {
    
    TransactionDTO transactionDTO = TransactionDTO.builder()
        .valor(100d)
        .numeroCartao(defaultCardNumber)
        .senhaCartao("????")
        .build();

    return transactionDTO;
  }    
  
  /**
   * 
   * @return
   */
  public static final TransactionDTO buildNewTransactionDTOWithoutFunds() {

    TransactionDTO transactionDTO = TransactionDTO.builder()
        .valor(9999999999d)
        .numeroCartao(defaultCardNumber)
        .senhaCartao(defaultPassword)        
        .build();

    return transactionDTO;
  }   
  
}
