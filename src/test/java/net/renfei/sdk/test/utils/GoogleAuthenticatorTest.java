package net.renfei.sdk.test.utils;

import net.renfei.sdk.utils.GoogleAuthenticator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 谷歌身份验证器测试
 *
 * @author RenFei
 */
public class GoogleAuthenticatorTest {
    @Test
    public void testGoogleAuthenticator() {
        System.out.println("==== " + this.getClass().getName() + " ====");
        String secretKey = GoogleAuthenticator.generateSecretKey("abc123");
        String totp = GoogleAuthenticator.genTotpString("RENFEI.NET", "Tester", secretKey);
        Assertions.assertFalse(GoogleAuthenticator.authcode("123456", secretKey));
    }
}
