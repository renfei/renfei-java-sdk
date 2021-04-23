package net.renfei.sdk.test.utils;

import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.PasswordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordUtilsTest extends Tests {
    @Test
    public void testPasswordUtils() throws PasswordUtils.CannotPerformOperationException {
        System.out.println("==== " + this.getClass().getName() + " ====");
        String password = "MyPassword", correctHash;
        correctHash = PasswordUtils.createHash(password);
        Assertions.assertTrue(PasswordUtils.verifyPassword(password, correctHash));
        Assertions.assertFalse(PasswordUtils.verifyPassword(password, ""));
        Assertions.assertFalse(PasswordUtils.verifyPassword(password, "test:test:test"));
    }
}
