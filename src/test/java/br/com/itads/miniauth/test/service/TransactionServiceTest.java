package br.com.itads.miniauth.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.itads.miniauth.dto.TransactionDTO;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.exception.InvalidCardFormatException;
import br.com.itads.miniauth.exception.NoRefundsException;
import br.com.itads.miniauth.exception.PasswordInvalidException;
import br.com.itads.miniauth.model.Card;
import br.com.itads.miniauth.repository.CardRepository;
import br.com.itads.miniauth.services.impl.TransactionServiceImpl;
import br.com.itads.miniauth.services.interfaces.CardService;
import br.com.itads.miniauth.test.builder.CardBuilder;
import br.com.itads.miniauth.test.builder.TransactionBuilder;

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
  @InjectMocks
  TransactionServiceImpl transactionService;
  

  /**
   * 
   */
  @Mock
  CardService cardService;

  /**
   * compre - rf - deb cri cra - papel brk adm (12:00 a 16:00)
   * 
   * pré - banco fibra 13.5 2029
   * pré - banco vontorantim 12.80 93% cdi 
   * 
   * banco mercantil
   * 
   */
  @Mock
  CardRepository repository; 
  
  /**
   * @throws InvalidCardFormatException 
   * @throws CardNotFoundException 
   * @throws PasswordInvalidException 
   * 
   */
  @Test
  void whenTransactionDebitANormalValue() throws CardNotFoundException, InvalidCardFormatException, PasswordInvalidException {

    Card card = CardBuilder.buildNewCardLikeReadMe();
    
    when(cardService.findCardByNumber(anyString())).thenReturn(card);
    
    TransactionDTO dto = TransactionBuilder.buildNewTransactionDTOLikeReadMe();
    
    assertDoesNotThrow(() -> transactionService.processTransaction(dto));

  }

  /**
   * @throws InvalidCardFormatException 
   * @throws CardNotFoundException 
   * @throws PasswordInvalidException 
   * 
   */
  @Test
  void whenTransactionExcededTheFunds() throws CardNotFoundException, InvalidCardFormatException, PasswordInvalidException {

    Card card = CardBuilder.buildNewCardWithoutFunds();
    
    when(cardService.findCardByNumber(anyString())).thenReturn(card);
    
    TransactionDTO dto = TransactionBuilder.buildNewTransactionDTOWithoutFunds();

    assertThrows(NoRefundsException.class, () -> transactionService.processTransaction(dto));

  }
  
}
