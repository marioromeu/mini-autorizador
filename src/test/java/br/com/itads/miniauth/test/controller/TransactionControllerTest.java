package br.com.itads.miniauth.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.itads.miniauth.cache.RedisCacheRepository;
import br.com.itads.miniauth.controller.impl.TransactionController;
import br.com.itads.miniauth.dto.TransactionDTO;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.exception.NoRefundsException;
import br.com.itads.miniauth.exception.PasswordInvalidException;
import br.com.itads.miniauth.services.impl.TransactionServiceImpl;
import br.com.itads.miniauth.services.interfaces.CardService;
import br.com.itads.miniauth.test.builder.TransactionBuilder;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
public class TransactionControllerTest {

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
  private RedisCacheRepository redisRepository;

  /**
   * 
   */
  @MockBean
  private TransactionServiceImpl service;
  
  /**
   * 
   */
  @MockBean
  private CardService cardService;  
  
  /**
   * Transactions
   */
  private final TransactionDTO newTransactionOK             = TransactionBuilder.buildNewTransactionDTOLikeReadMe();
  private final TransactionDTO newTransactionCardNotFound   = TransactionBuilder.buildNewTransactionDTOCardNotFound();
  private final TransactionDTO newTransactionPassWrong      = TransactionBuilder.buildNewTransactionWithCardWrongPasswd();
  private final TransactionDTO newTransactionNoRefunds      = TransactionBuilder.buildNewTransactionDTOWithoutFunds();
  
  private final static String PATH = "/transacoes";
  private final static String APPLICATION_JSON = "application/json";

  /**
   * 
   * @throws Exception
   */
  @Test
  void whenTransactionOKReturns201() throws Exception {
      mockMvc.perform(post(PATH)
                      .contentType(APPLICATION_JSON)
                      .accept(APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(newTransactionOK)))
              .andExpect(status().isCreated());
  }

  /**
   * 
   * @throws Exception
   */
  @Test
  void whenTransactionWithCardNotFoundReturns422() throws Exception {

      doThrow(CardNotFoundException.class).when(service).processTransaction(any());

      mockMvc.perform(post(PATH)
                      .contentType(APPLICATION_JSON)
                      .accept(APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(newTransactionCardNotFound)))
              .andExpect(status().isUnprocessableEntity());
  }

  /**
   * 
   * @throws Exception
   */
  @Test
  void whenTransactionWithCardPasswordWrongReturns422() throws Exception {
      
      doThrow(PasswordInvalidException.class).when(service).processTransaction(any());

      mockMvc.perform(post(PATH)
                      .contentType(APPLICATION_JSON)
                      .accept(APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(newTransactionPassWrong)))
              .andExpect(status().isUnprocessableEntity());
  }

  /**
   * 
   * @throws Exception
   */
  @Test
  void whenTransactionWithoutRefundsReturns422() throws Exception {
      doThrow(NoRefundsException.class).when(service).processTransaction(any());

      mockMvc.perform(post(PATH)
                      .contentType(APPLICATION_JSON)
                      .accept(APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(newTransactionNoRefunds)))
              .andExpect(status().isUnprocessableEntity());
  }

}
