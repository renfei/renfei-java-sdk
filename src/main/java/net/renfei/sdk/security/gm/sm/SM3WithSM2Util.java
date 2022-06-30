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
package net.renfei.sdk.security.gm.sm;

import net.renfei.sdk.security.gm.sm.random.SecureRandomFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.util.Base64;

/**
 * SM3的SM2签名
 * 先散列然后非对称签名
 * 直接签名内容会很大所以先散列
 *
 * @author renfei
 */
public class SM3WithSM2Util extends AbstractSM {
    private final Signature signature;

    public SM3WithSM2Util() throws NoSuchProviderException, NoSuchAlgorithmException {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        signature = Signature.getInstance(SM3SM2_VALUE, BouncyCastleProvider.PROVIDER_NAME);
    }

    /**
     * 摘要并签名
     *
     * @param privateKey 私钥
     * @param message    内容
     * @return
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    public String digestAndSign(PrivateKey privateKey, String message) throws SignatureException, InvalidKeyException {
        return Base64.getEncoder().encodeToString(digestAndSign(privateKey, message.getBytes()));
    }

    /**
     * 验证签名和摘要
     *
     * @param publicKey 公钥
     * @param message   内容
     * @param sigBytes  签名
     * @return
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public boolean verifySignatureAndDigest(PublicKey publicKey, String message, String sigBytes) throws InvalidKeyException, SignatureException {
        return verifySignatureAndDigest(publicKey, message.getBytes(), Base64.getDecoder().decode(sigBytes));
    }

    /**
     * 摘要并签名
     *
     * @param privateKey 私钥
     * @param message    内容
     * @return
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    public byte[] digestAndSign(PrivateKey privateKey, byte[] message) throws SignatureException, InvalidKeyException {
        byte[] hashVal = SM3Util.hash(message);
        synchronized (this) {
            signature.initSign(privateKey, SecureRandomFactory.getSecureRandom());
            signature.update(hashVal);
            return signature.sign();
        }
    }

    /**
     * 验证签名和摘要
     *
     * @param publicKey 公钥
     * @param message   内容
     * @param sigBytes  签名
     * @return
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public boolean verifySignatureAndDigest(PublicKey publicKey, byte[] message, byte[] sigBytes) throws InvalidKeyException, SignatureException {
        byte[] hashVal = SM3Util.hash(message);
        synchronized (this) {
            signature.initVerify(publicKey);
            signature.update(hashVal);
            return signature.verify(sigBytes);
        }
    }
}
