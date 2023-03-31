package br.com.itads.miniauth.dto;

import br.com.itads.miniauth.model.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessTransactionDTO {

  /**
   * 
   */
  private Card card;
  
  /**
   * 
   */
  private Double valueOfTransaction;
  
}
