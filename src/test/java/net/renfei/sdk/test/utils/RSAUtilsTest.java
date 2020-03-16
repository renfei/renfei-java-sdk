package net.renfei.sdk.test.utils;

import net.renfei.sdk.utils.RSAUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * RSA加解密测试
 *
 * @author RenFei
 */
public class RSAUtilsTest {
    @Test
    public void reaTest() {
        Map<Integer, String> keyMap = RSAUtils.genKeyPair(2048);
        //加密字符串
        String message = "test";
        try {
            String encrypt = RSAUtils.encrypt(message, keyMap.get(0));
            System.out.println(keyMap.get(0));
            System.out.println(encrypt);
            System.out.println(keyMap.get(1));
            Assert.assertEquals(message, RSAUtils.decrypt(encrypt, keyMap.get(1)));
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.assertNull(ex);
        }
    }
}
