package br.com.itads.miniauth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CARD")
public class Card { 
  
  /**
   * 
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  /**
   * 
   */
  @Column(nullable = false)
  private Double funds;
  
  /**
   * 
   */
  @Column(nullable = false, length = 16, unique = true)
  private String number; 
  
  /**
   * 
   */
  @Column(nullable = false)
  private String password;  
  
}
