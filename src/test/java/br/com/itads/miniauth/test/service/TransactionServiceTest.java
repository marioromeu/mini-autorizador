package br.com.itads.miniauth.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.itads.miniauth.dto.TransactionDTO;
import br.com.itads.miniauth.services.impl.TransactionServiceImpl;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

  /**
   * 
   */
  TransactionServiceImpl service;
  
  /**
   * 
   */
  @Test
  void whenTransactionDebitANormalValue() {

    TransactionDTO dto = TransactionDTO.builder()
        .numeroCartao("6549873025634501")
        .senhaCartao("1234")
        .valor(100d)
        .build();

    assertDoesNotThrow(() -> service.createNewTransaction(dto));

  }

  /**
   * 
   */
  @Test
  void whenTransactionExcededTheFunds() {

    TransactionDTO dto = TransactionDTO.builder()
        .numeroCartao("6549873025634501")
        .senhaCartao("1234")
        .valor(600d)
        .build();

    assertDoesNotThrow(() -> service.createNewTransaction(dto));

  }
  
}
