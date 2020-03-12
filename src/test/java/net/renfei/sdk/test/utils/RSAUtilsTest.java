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
        Map<Integer, String> keyMap = RSAUtils.genKeyPair(1024);
        //加密字符串
        String message = "test";
        try {
            String encrypt = RSAUtils.encrypt(message, keyMap.get(0));
            Assert.assertEquals(message, RSAUtils.decrypt(encrypt, keyMap.get(1)));
        } catch (Exception ex) {
            Assert.assertNull(ex);
        }
    }
}
