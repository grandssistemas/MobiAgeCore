package br.com.codein.mobiagecore.utils;

import br.com.codein.mobiagecore.AbstractTest;
import br.com.codein.mobiagecore.domain.utils.PasswordUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PasswordUtilsTest extends AbstractTest {

    @Test
    public void testDescrypt() {
        String pass = "SenhaParaTeste$%";
        String p2 = "Ap6cyHYExECgSJwYeDO+eOXgWiIdqNSdBZdBXvtBb5c=";
        try {
            String passToDescrypt = PasswordUtils.encrypt(pass.toCharArray());
            String passDescrypt = PasswordUtils.decrypt(passToDescrypt.toCharArray());
            assertEquals(pass, passDescrypt);
            assertEquals(p2,passToDescrypt);
        } catch (Exception e) {
            fail();
        }
    }
}
