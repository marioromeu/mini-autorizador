package br.com.itads.miniauth.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import br.com.itads.miniauth.exception.PasswordInvalidException;

/**
 *
 * @author marioromeu
 * @email mario.romeu@gmail.com
 */
public abstract class SecurityUtils {

  /**
   * 
   */
  private static final String ALGORITHM = "PBEWithMD5AndDES";
  
  /**
   * 
   */
  private static final String SECRET_KEY = "00263E3B263477002224372C770E2C24202C2526233F223E";
  
  /**
   * 
   */
  private static final String CRYPT_PREFIX = "crypt@";

  /**
   * 
   */
  private static SecretKey secretKey;
  
  /**
   * 
   */
  private static PBEParameterSpec parameterSpec;

  /**
   * 
   */
  static {
    
    SecretKeyFactory secretKeyFactory;
    
    try {
      secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
      KeySpec keySpec = new PBEKeySpec(SECRET_KEY.toCharArray());
      secretKey = secretKeyFactory.generateSecret(keySpec);
      parameterSpec = new PBEParameterSpec(new byte[] {1, 9, 0, 8, 1, 9, 8, 9}, 20);
      
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      
    } catch (InvalidKeySpecException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      
    }
    
  }

  /**
   *
   * @param text
   * @return
   */
  public static boolean isEncrypted(String text) {
    return text.startsWith(CRYPT_PREFIX);
  }

  /**
   *
   * @param text
   * @return
   */
  public static String encrypt(String text) {
    String cryptPassword = "";
    
    try {
      Cipher cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);
      cryptPassword =
          CRYPT_PREFIX.concat(Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes())));
      
    } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException
        | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
      System.out.println("Error encrypting");
      
    }
    
    return cryptPassword;
    
  }

  /**
   *
   * @param text
   * @return
   * @throws Exception
   */
  public static String decrypt(String text) throws Exception {
    
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

    String encrypted = text.replace(CRYPT_PREFIX, "");
    
    return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));

  }

  /**
   * 
   * @param card
   * @param password
   * @return
   */
  public static Boolean isPasswordValid(String cardPassword, String password) throws PasswordInvalidException {

    String encryptedPassword = SecurityUtils.encrypt( password );

    if (!cardPassword.equals(encryptedPassword)) {
    
      throw new PasswordInvalidException() ;
     
    } 
    
    return true;

  }
  
}
