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

import net.renfei.sdk.security.gm.sm.SM2Util;
import net.renfei.sdk.security.gm.sm.SM3WithSM2Util;
import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.StringUtil;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author renfei
 */
public class SM3WithSM2UtilTest extends Tests {

    static int randomData = 128;
    static byte[] message = StringUtil.getRandomString(randomData).getBytes();
    PublicKey pubKey;
    PrivateKey privKey;
    KeyPair keyPair;

    static String exceptionHappened = "Exception happened";

    @Test
    public void threadSafe() throws Exception {
        Queue<Boolean> results = new ConcurrentLinkedQueue<>();
        Queue<Exception> ex = new ConcurrentLinkedQueue<>();

        SM2Util sm2instance = new SM2Util();
        this.keyPair = sm2instance.generatekeyPair();
        this.pubKey = keyPair.getPublic();
        this.privKey = keyPair.getPrivate();

        SM3WithSM2Util instance = new SM3WithSM2Util();
        byte[] signresult = instance.digestAndSign(this.privKey, message);
        assertTrue(instance.verifySignatureAndDigest(this.pubKey, message, signresult));
        // 多线程，线程安全测试
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                try {
                    byte[] result = instance.digestAndSign(this.privKey, message);
                    results.add(instance.verifySignatureAndDigest(this.pubKey, message, result));
                } catch (Exception e) {
                    ex.add(e);
                }
            }).start();
        }
        while (!ex.isEmpty()) {
            Exception e = ex.poll();
            e.printStackTrace();
            fail(exceptionHappened);
        }
        Thread.sleep(5000);
        assertEquals(300, results.size());
        while (!results.isEmpty()) {
            assertTrue(results.poll());
        }
    }

    @Test
    public void threadSafeString() throws Exception {
        Queue<Boolean> results = new ConcurrentLinkedQueue<>();
        Queue<Exception> ex = new ConcurrentLinkedQueue<>();

        SM2Util sm2instance = new SM2Util();
        this.keyPair = sm2instance.generatekeyPair();
        this.pubKey = keyPair.getPublic();
        this.privKey = keyPair.getPrivate();
        String messageString = StringUtil.getRandomString(16);

        SM3WithSM2Util instance = new SM3WithSM2Util();
        String signresult = instance.digestAndSign(this.privKey, messageString);
        assertTrue(instance.verifySignatureAndDigest(this.pubKey, messageString, signresult));
        // 多线程，线程安全测试
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                try {
                    String result = instance.digestAndSign(this.privKey, messageString);
                    results.add(instance.verifySignatureAndDigest(this.pubKey, messageString, result));
                } catch (Exception e) {
                    ex.add(e);
                }
            }).start();
        }
        while (!ex.isEmpty()) {
            Exception e = ex.poll();
            e.printStackTrace();
            fail(exceptionHappened);
        }
        Thread.sleep(5000);
        assertEquals(300, results.size());
        while (!results.isEmpty()) {
            assertTrue(results.poll());
        }
    }
}
