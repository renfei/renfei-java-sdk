package net.renfei.sdk.test.utils;

import net.renfei.sdk.test.TestApplication;
import net.renfei.sdk.utils.PasswordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordUtilsTest extends TestApplication {
    @Test
    public void testPassword() throws PasswordUtils.CannotPerformOperationException {
        String password = "MyPassword", correctHash;
        correctHash = PasswordUtils.createHash(password);
        Assertions.assertTrue(PasswordUtils.verifyPassword(password, correctHash));
    }
}
