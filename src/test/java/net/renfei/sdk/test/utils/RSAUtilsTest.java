package net.renfei.sdk.test.utils;

import net.renfei.sdk.test.TestApplication;
import net.renfei.sdk.utils.RSAUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * RSA加解密测试
 *
 * @author RenFei
 */
public class RSAUtilsTest extends TestApplication {
        @Test
        public void reaTest() {
            System.out.println("==== " + this.getClass().getName() + " ====");
            //服务器端的
            Map<Integer, String> serverKeyMap = RSAUtils.genKeyPair(2048);
            //客户端的
            Map<Integer, String> clientKeyMap = RSAUtils.genKeyPair(1024);
            String clientPubKey = clientKeyMap.get(0);
            try {
                //客户端使用服务器公钥加密自己的公钥
                String encrypt = RSAUtils.encrypt(clientPubKey, serverKeyMap.get(0));
                //服务器端使用自己的私钥解密拿到客户端公钥
                Assertions.assertEquals(clientPubKey, RSAUtils.decrypt(encrypt, serverKeyMap.get(1)));
                String AESkey = "aeskey123456";
                //使用客户端的公钥加密AES的秘钥
                String encryptAES = RSAUtils.encrypt(AESkey, clientPubKey);
                //客户端用自己的私钥解密拿到AES的秘钥
                Assertions.assertEquals(AESkey, RSAUtils.decrypt(encryptAES, clientKeyMap.get(1)));
            } catch (Exception ex) {
                ex.printStackTrace();
                Assertions.assertNull(ex);
            }
        }
}
