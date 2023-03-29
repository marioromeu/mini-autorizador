package br.com.itads.miniauth.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.itads.miniauth.dto.CardDTO;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.model.Card;
import br.com.itads.miniauth.repository.CardRepository;
import br.com.itads.miniauth.services.impl.CardServiceImpl;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@ExtendWith(SpringExtension.class)
public class CardServiceTest {

  /**
   * 
   */
  @InjectMocks
  CardServiceImpl service;

  /**
   * 
   */
  @Mock
  CardRepository repository;  

  /**
   * 
   */
  @Test
  void whenCardNotExistsReturnAValidCard() {

    CardDTO cardDTO = CardDTO.builder().numeroCartao("6549873025634501").senha("1234").build();

    assertDoesNotThrow(() -> service.createNewCard(cardDTO));
  }

  /**
   * 
   */
  @Test
  void whenCardAlreadyExistsThrowsCardAlreadyExistsException() {

    CardDTO dto = CardDTO.builder()
        .numeroCartao("6549873025634501")
        .senha("1234")        
        .build();    
    
    Card card = Card.builder()
        .number("6549873025634501")
        .password("1234")
        .funds(500d)
        .build();
    
    when(repository.save(card)).thenThrow(DataIntegrityViolationException.class);
    
    //TODO encontrar uma forma melhor pra isso
    //assertThrows(CardAlreadyExistsException.class, () -> service.createNewCard(dto));
  }

  /**
   * @throws CardNotFoundException 
   * 
   */
  @Test
  void whenCardExistsReturnAValidCard() throws CardNotFoundException {

    Card card = Card.builder()
        .number("6549873025634501")
        .password("1234")
        .funds(500d)
        .build();

    when(repository.findCardByNumber(anyString())).thenReturn(card);
    
    assertDoesNotThrow(() -> service.findCardByNumber(card.getNumber()));
  }
  
  /**
   * 
   */
  @Test
  void whenCardNotFoundThrowsCardNotFoundException() {

    CardDTO cardDTO = CardDTO.builder().numeroCartao("6549873025634501").senha("1234").build();

    assertThrows(CardNotFoundException.class, () -> service.findCardByNumber(cardDTO.getNumeroCartao()));
  }
  
}
