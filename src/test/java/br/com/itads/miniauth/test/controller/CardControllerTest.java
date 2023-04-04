package br.com.itads.miniauth.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.itads.miniauth.cache.RedisCacheRepository;
import br.com.itads.miniauth.controller.impl.CardController;
import br.com.itads.miniauth.dto.CardDTO;
import br.com.itads.miniauth.exception.CardAlreadyExistsException;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.exception.InvalidCardFormatException;
import br.com.itads.miniauth.model.Card;
import br.com.itads.miniauth.repository.CardRepository;
import br.com.itads.miniauth.services.impl.CardServiceImpl;
import br.com.itads.miniauth.test.builder.CardBuilder;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(CardController.class)
@AutoConfigureMockMvc
public class CardControllerTest {

  /**
   * 
   */
  @Autowired
  private MockMvc mockMvc;

  /**
   * 
   */
  @Autowired
  private ObjectMapper objectMapper;

  /**
   * 
   */
  @MockBean
  private CardServiceImpl service;

  /**
   * 
   */
  @Mock
  CardRepository repository;    
  
  /**
   * 
   */
  @MockBean
  RedisCacheRepository redisRepository;

  /**
   * Cards
   */
  private final Card validCard = CardBuilder.buildNewCardLikeReadMe(); 
  private final Card validCardCleanPasswd = CardBuilder.buildNewCardCleanPassword();  
  private final CardDTO cardDTOWithPassClean = CardBuilder.buildCardDTOBasedOnCard(validCardCleanPasswd);


  private final static String PATH = "/cartoes";
  private final static String APPLICATION_JSON = "application/json";

  /**
   * 
   * @throws Exception
   */
  @Test
  void whenCreateCardReturn201() throws Exception {
    
    when(service.createNewCard(cardDTOWithPassClean)).thenReturn(validCard);
        
    mockMvc.perform(post(PATH)
        .contentType(APPLICATION_JSON)
        .accept(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cardDTOWithPassClean)))
    .andExpect(status().isCreated());

  }

  /**
   * 
   * @throws Exception
   */
  @Test
  void whenCardAlreadyExistsReturn422() throws Exception {
    
      when(service.createNewCard(any())).thenThrow(CardAlreadyExistsException.class);

      mockMvc.perform(post(PATH)
                      .contentType(APPLICATION_JSON)
                      .accept(APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(cardDTOWithPassClean)))
              .andExpect(status().isUnprocessableEntity());
  }

  /**
   * 
   * @throws Exception
   */
  @Test
  void whenInvalidCardExistsReturn422() throws Exception {
    
    when(service.createNewCard(any())).thenThrow(InvalidCardFormatException.class);

      mockMvc.perform(post(PATH)
                      .contentType(APPLICATION_JSON)
                      .accept(APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(cardDTOWithPassClean)))
             .andExpect(status().isUnprocessableEntity());
  }

  /**
   * 
   * @throws Exception
   */
  @Test
  void whenGetAValidCardRetorn200() throws Exception {
      when(service.findCardByNumber(any())).thenReturn(validCard);

      mockMvc.perform(get(PATH + "/{numeroCartao}", cardDTOWithPassClean.getNumeroCartao())
                      .contentType(APPLICATION_JSON)
                      .accept(APPLICATION_JSON))
              .andExpect(status().isOk());

  }

  /**
   * 
   * @throws Exception
   */
  @Test
  void whenGetAInvalidCardRetorn404() throws Exception {
    
      when(service.findCardByNumber(any())).thenThrow(CardNotFoundException.class);

      mockMvc.perform(get(PATH + "/{numeroCartao}", cardDTOWithPassClean.getNumeroCartao())
                      .contentType(APPLICATION_JSON)
                      .accept(APPLICATION_JSON))
              .andExpect(status().isNotFound());
  }


}
