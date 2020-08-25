package net.renfei.sdk.test.utils;

import lombok.SneakyThrows;
import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.GoogleAuthenticator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 谷歌身份验证器测试
 *
 * @author RenFei
 */
public class GoogleAuthenticatorTest extends Tests {
    @Test
    public void testGoogleAuthenticator() throws InterruptedException {
        Runnable taskTemp = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("==== " + this.getClass().getName() + " ====");
                GoogleAuthenticator.setWindowSize(2);
                String secretKey = GoogleAuthenticator.generateSecretKey("abc123");
                String totp = GoogleAuthenticator.genTotpString("RENFEI.NET", "Tester", secretKey);
                Assertions.assertFalse(GoogleAuthenticator.authcode("123456", secretKey));
                Assertions.assertFalse(GoogleAuthenticator.authcode("abc", secretKey));
            }
        };
        startTaskAllInOnce(100, taskTemp);
    }
}
