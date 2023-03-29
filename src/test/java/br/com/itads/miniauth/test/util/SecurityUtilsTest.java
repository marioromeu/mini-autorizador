package br.com.itads.miniauth.test.util;

import br.com.itads.miniauth.util.SecurityUtils;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 */
public class SecurityUtilsTest {


  public static void main(String[] args) throws Exception {
    
    String encrypted = SecurityUtils.encrypt("clean2");
    
    System.out.println(SecurityUtils.isPasswordValid("clean", encrypted));
        
  }
  
}
