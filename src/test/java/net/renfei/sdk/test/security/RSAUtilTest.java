/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.sdk.test.security;

import net.renfei.sdk.test.Tests;
import net.renfei.sdk.security.RSAUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author renfei
 */
public class RSAUtilTest extends Tests {
    @Test
    public void testRSAUtils() {
        System.out.println("==== " + this.getClass().getName() + " ====");
        //服务器端的
        Map<Integer, String> serverKeyMap = RSAUtil.genKeyPair(2048);
        //客户端的
        Map<Integer, String> clientKeyMap = RSAUtil.genKeyPair(1024);
        assert clientKeyMap != null;
        String clientPubKey = clientKeyMap.get(0);
        try {
            //客户端使用服务器公钥加密自己的公钥
            assert serverKeyMap != null;
            String encrypt = RSAUtil.encrypt(clientPubKey, serverKeyMap.get(0));
            //服务器端使用自己的私钥解密拿到客户端公钥
            Assertions.assertEquals(clientPubKey, RSAUtil.decrypt(encrypt, serverKeyMap.get(1)));
            String AESkey = "aeskey123456";
            //使用客户端的公钥加密AES的秘钥
            String encryptAES = RSAUtil.encrypt(AESkey, clientPubKey);
            //客户端用自己的私钥解密拿到AES的秘钥
            Assertions.assertEquals(AESkey, RSAUtil.decrypt(encryptAES, clientKeyMap.get(1)));
            Assertions.assertNull(RSAUtil.getPublicKey(""));
            Assertions.assertNull(RSAUtil.getPrivateKey(""));
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.assertNull(ex);
        }
    }
}
