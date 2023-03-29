package br.com.itads.miniauth.test.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import br.com.itads.miniauth.exception.PasswordInvalidException;
import br.com.itads.miniauth.util.SecurityUtils;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 */
public class SecurityUtilsTest {

  @Test
  void whenPasswordIsDifferentThrowsPasswordInvalidException() {

    String cleanPassword = "1234";
    String encryptedPassword = SecurityUtils.encrypt(cleanPassword);

    assertThrows(PasswordInvalidException.class,
        () -> SecurityUtils.isPasswordValid(encryptedPassword, cleanPassword + "1"));
  }

  @Test
  void whenPasswordIsEqualsReturnTrue() throws PasswordInvalidException {

    String cleanPassword = "1234";
    String encryptedPassword = SecurityUtils.encrypt(cleanPassword);

    Boolean bool = SecurityUtils.isPasswordValid(encryptedPassword, cleanPassword);

    assertTrue(bool);
  }

}
