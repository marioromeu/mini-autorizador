package br.com.itads.miniauth.dto;

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
public class TransactionDTO {

  /**
   * 
   */
  private String numeroCartao;

  /**
   * 
   */
  private String senhaCartao;  
  
  /**
   * 
   */
  private Double valor;
  
}
