package net.renfei.sdk.test.utils;

import net.renfei.sdk.utils.PasswordUtils;
import org.junit.Assert;
import org.junit.Test;

public class PasswordUtilsTest {
    @Test
    public void testPassword() throws PasswordUtils.CannotPerformOperationException {
        String password = "MyPassword", correctHash;
        correctHash = PasswordUtils.createHash(password);
        Assert.assertTrue(PasswordUtils.verifyPassword(password, correctHash));
    }
}
