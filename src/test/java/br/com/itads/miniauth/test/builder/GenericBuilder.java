package br.com.itads.miniauth.test.builder;

import br.com.itads.miniauth.util.SecurityUtils;

/**
 * 
 * @author marioromeu
 * @email  mario.romeu@gmail.com
 *
 */
public abstract class GenericBuilder {

  /**
   * 
   */
  public static final String defaultPassword = "1234";
  
  /**
   * 
   */
  public static final String encryptedDefaultPassword = SecurityUtils.encrypt(defaultPassword);  
  
  /**
   * 
   */
  public static final String defaultCardNumber = "6549873025634501";
  
}
