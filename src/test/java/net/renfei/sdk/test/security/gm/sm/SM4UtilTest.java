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
package net.renfei.sdk.test.security.gm.sm;

import net.renfei.sdk.security.gm.sm.SM4Cipher;
import net.renfei.sdk.security.gm.sm.SM4ModeAndPaddingEnum;
import net.renfei.sdk.security.gm.sm.SM4Util;
import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.StringUtil;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author renfei
 */
public class SM4UtilTest extends Tests {
    private static byte[] content = null;
    private static final byte[] content16 = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
    private static byte[] iv = null;
    private static SM4ModeAndPaddingEnum type;
    static int randomData = 128;
    static String message = StringUtil.getRandomString(randomData);
    static String exceptionHappened = "Exception happened";

    public SM4UtilTest() throws NoSuchProviderException, NoSuchAlgorithmException {
        Object[][] object = {
                {content16, SM4ModeAndPaddingEnum.SM4_ECB_NoPadding, false},
                {message.getBytes(StandardCharsets.UTF_8), SM4ModeAndPaddingEnum.SM4_ECB_PKCS5Padding, false},
                {message.getBytes(StandardCharsets.UTF_8), SM4ModeAndPaddingEnum.SM4_ECB_PKCS7Padding, false},
                {content16, SM4ModeAndPaddingEnum.SM4_CBC_NoPadding, true},
                {message.getBytes(StandardCharsets.UTF_8), SM4ModeAndPaddingEnum.SM4_CBC_PKCS5Padding, true},
                {message.getBytes(StandardCharsets.UTF_8), SM4ModeAndPaddingEnum.SM4_CBC_PKCS7Padding, true},
                {message.getBytes(StandardCharsets.UTF_8), SM4ModeAndPaddingEnum.SM4_CFB_NoPadding, true},
                {message.getBytes(StandardCharsets.UTF_8), SM4ModeAndPaddingEnum.SM4_OFB_NoPadding, true},
                {message.getBytes(StandardCharsets.UTF_8), SM4ModeAndPaddingEnum.SM4_CTR_NoPadding, true}
        };
        SM4UtilTest.content = (byte[]) object[1][0];
        SM4UtilTest.type = (SM4ModeAndPaddingEnum) object[1][1];
        boolean flag = (boolean) object[1][2];
        if (flag) {
            SM4Util instance = new SM4Util();
            iv = instance.generateKey();
        }
    }

    @Test
    public void testingSM4() throws Exception {
        SM4Cipher sm4Cipher = new SM4Cipher();
        Cipher cipher = sm4Cipher.getCipher(type);
        SM4Util instance = new SM4Util();
        byte[] key = instance.generateKey();
        SecretKeySpec sm4Key = new SecretKeySpec(key, type.getName());
        System.out.println("===== " + type + " =====");
        // 加密
        byte[] v = instance.encrypt(cipher, content, sm4Key, iv);
        // 解密
        byte[] c = instance.decrypt(cipher, v, sm4Key, iv);

        System.out.println("解密内容：" + new String(c));
        assertArrayEquals(c, content);
    }

    @Test
    public void threadsafe() throws Exception {
        SM4Cipher sm4Cipher = new SM4Cipher();
        Queue<byte[]> results = new ConcurrentLinkedQueue<>();
        Queue<Exception> ex = new ConcurrentLinkedQueue<>();
        SM4Util instance = new SM4Util();
        byte[] key = instance.generateKey();
        SecretKeySpec sm4Key = new SecretKeySpec(key, type.getName());
        byte[] v;
        Cipher cipher = sm4Cipher.getCipher(type);
        System.out.println("===== " + type + " =====");
        // 加密
        v = instance.encrypt(cipher, content, sm4Key, iv);
        // 解密
        byte[] c = instance.decrypt(cipher, v, sm4Key, iv);

        System.out.println("解密内容：" + new String(c));
        assertArrayEquals(c, content);
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                try {
                    SM4Cipher sm4Ciphertest = new SM4Cipher();
                    Cipher ciphertest = sm4Ciphertest.getCipher(type);
                    results.add(instance.decrypt(ciphertest, v, sm4Key, iv));
                } catch (Exception e) {
                    ex.add(e);
                }
            }).start();
        }
        Thread.sleep(5000);
        while (!ex.isEmpty()) {
            Exception e = ex.poll();
            e.printStackTrace();
            fail(exceptionHappened);
        }
        assertEquals(300, results.size());
        while (!results.isEmpty()) {
            assertArrayEquals(results.poll(), content);
        }
    }
}
