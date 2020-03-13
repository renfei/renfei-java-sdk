package net.renfei.sdk.test.utils;

import net.renfei.sdk.utils.GoogleAuthenticator;
import org.junit.Assert;
import org.junit.Test;

/**
 * 谷歌身份验证器测试
 *
 * @author RenFei
 */
public class GoogleAuthenticatorTest {
    @Test
    public void test() {
        String secretKey = GoogleAuthenticator.generateSecretKey("abc123");
        String totp = GoogleAuthenticator.genTotpString("RENFEI.NET", "Tester", secretKey);
        Assert.assertFalse(GoogleAuthenticator.authcode("123456", secretKey));
    }
}
