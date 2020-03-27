package net.renfei.sdk.test.utils;

import net.renfei.sdk.test.TestApplication;
import net.renfei.sdk.utils.AESUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * AES对称加解密
 *
 * @author RenFei
 */
public class AESUtilTest extends TestApplication {
    @Test
    public void aesTest() throws Exception {
        System.out.println("==== " + this.getClass().getName() + " ====");
        String key = "abcdefghiklmnopq";
        String message = "test";
        String encryptedString = AESUtil.encrypt(message, key);
        String decryptedString = AESUtil.decrypt(encryptedString, key);
        System.out.println("Key --> " + key);
        System.out.println("encryptedString --> " + encryptedString);
        System.out.println("decryptedString --> " + decryptedString);
        Assertions.assertEquals(message, decryptedString);
    }
}
