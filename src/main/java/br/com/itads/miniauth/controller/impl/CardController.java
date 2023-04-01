package br.com.itads.miniauth.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import br.com.itads.miniauth.aspect.LogExecutionTime;
import br.com.itads.miniauth.aspect.MethodLogging;
import br.com.itads.miniauth.controller.interfaces.CardControllerInterface;
import br.com.itads.miniauth.dto.CardDTO;
import br.com.itads.miniauth.exception.CardAlreadyExistsException;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.exception.InvalidCardFormatException;
import br.com.itads.miniauth.model.Card;
import br.com.itads.miniauth.responses.CardResponse;
import br.com.itads.miniauth.services.interfaces.CardService;
import br.com.itads.miniauth.util.SecurityUtils;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@RestController
public class CardController implements CardControllerInterface {

  /**
   * 
   */
  @Autowired
  private CardService service;

  /**
   * 
   */
  @LogExecutionTime
  @MethodLogging
  public ResponseEntity<CardResponse> createNewCard(
      MultiValueMap<String, String> header,
      CardDTO body) {

    ResponseEntity<CardResponse> responseEntity = null;

    CardResponse cardResponse = null;
    

    String cleanPassword = body.getSenha();
    
    try {
   
      String encriptedPassword = SecurityUtils.encrypt(cleanPassword);      
      
      body.setSenha(encriptedPassword);
      
      cardResponse =
          CardResponse.builder()
          .senha(encriptedPassword)
          .numeroCartao(body.getNumeroCartao())
          .build();
      
      service.createNewCard(body);
      
      responseEntity = new ResponseEntity<CardResponse>(cardResponse, HttpStatus.CREATED);

    } catch (CardAlreadyExistsException | InvalidCardFormatException e) {

      cardResponse.setSenha(cleanPassword);
      responseEntity = new ResponseEntity<CardResponse>(cardResponse, HttpStatus.UNPROCESSABLE_ENTITY);

    }

    return responseEntity;

  }

  /**
   * 
   */
  @LogExecutionTime  
  @MethodLogging  
  public ResponseEntity<Double> findCardByNumber(MultiValueMap<String, String> header,
      String numeroCartao) {

    ResponseEntity<Double> responseEntity = null;

    try {

      Card card = service.findCardByNumber(numeroCartao);

      responseEntity = new ResponseEntity<Double>(card.getFunds(), HttpStatus.OK);

    } catch (CardNotFoundException | InvalidCardFormatException e) {

      responseEntity = new ResponseEntity<Double>(HttpStatus.NOT_FOUND);

    }

    return responseEntity;

  }

}
