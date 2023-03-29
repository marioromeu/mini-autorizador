package br.com.itads.miniauth.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import br.com.itads.miniauth.controller.interfaces.CardControllerInterface;
import br.com.itads.miniauth.dto.CardDTO;
import br.com.itads.miniauth.exception.CardAlreadyExists;
import br.com.itads.miniauth.exception.CardNotFoundException;
import br.com.itads.miniauth.model.Card;
import br.com.itads.miniauth.responses.CardResponse;
import br.com.itads.miniauth.services.interfaces.CardService;

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
  public ResponseEntity<CardResponse> createNewCard(
      MultiValueMap<String, String> header,
      CardDTO body) {

    ResponseEntity<CardResponse> responseEntity = null;

    try {

      service.createNewCard(body);

      CardResponse cardResponse =
          CardResponse.builder()
          .senha(body.getSenha())
          .numeroCartao(body.getNumeroCartao())
          .build();

      responseEntity = new ResponseEntity<CardResponse>(cardResponse, HttpStatus.OK);

    } catch (CardAlreadyExists e) {

      responseEntity = new ResponseEntity<CardResponse>(HttpStatus.NOT_FOUND);

    }

    return responseEntity;

  }

  /**
   * 
   */
  public ResponseEntity<Double> findCardByNumber(MultiValueMap<String, String> header,
      String numeroCartao) {

    ResponseEntity<Double> responseEntity = null;

    try {

      Card card = service.findCardByNumber(numeroCartao);

      responseEntity = new ResponseEntity<Double>(card.getFunds(), HttpStatus.OK);

    } catch (CardNotFoundException e) {

      responseEntity = new ResponseEntity<Double>(HttpStatus.NOT_FOUND);

    }

    return responseEntity;

  }

}