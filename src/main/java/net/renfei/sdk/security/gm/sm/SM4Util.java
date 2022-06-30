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

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * 对称加密
 *
 * @author renfei
 */
public class SM4Util extends AbstractSM {
    private static final String ALGORITHM_NAME = "SM4";
    private static KeyGenerator kg;

    public SM4Util() throws NoSuchProviderException, NoSuchAlgorithmException {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
    }

    /**
     * SM4加密
     *
     * @param cipher cipher
     * @param input  明文数据
     * @param sm4Key SecretKeySpec
     * @param iv     初始向量(ECB模式下传NULL), IV must be 16 bytes long
     * @return byte[]
     * @throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException
     */
    public byte[] encrypt(Cipher cipher, byte[] input, SecretKeySpec sm4Key, byte[] iv) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        IvParameterSpec ivParameterSpec = null;
        if (iv != null) {
            ivParameterSpec = new IvParameterSpec(iv);
        }
        return sm4(input, sm4Key, cipher, ivParameterSpec, Cipher.ENCRYPT_MODE);
    }

    /**
     * SM4解密
     *
     * @param cipher cipher
     * @param input  密文数据
     * @param sm4Key SecretKeySpec
     * @param iv     初始向量(ECB模式下传NULL), IV must be 16 bytes long
     * @return byte[]
     * @throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException
     */
    public byte[] decrypt(Cipher cipher, byte[] input, SecretKeySpec sm4Key, byte[] iv) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        IvParameterSpec ivParameterSpec = null;
        if (iv != null) {
            ivParameterSpec = new IvParameterSpec(iv);
        }
        return sm4(input, sm4Key, cipher, ivParameterSpec, Cipher.DECRYPT_MODE);
    }

    /**
     * 执行sm4加解密
     *
     * @param input           明文或密文，与参数mode有关
     * @param sm4Key          密钥
     * @param cipher          chipher
     * @param ivParameterSpec 初始向量(ECB模式下传NULL)
     * @param mode            1-加密；2-解密
     * @return byte[]
     * @throws InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException
     */
    private static byte[] sm4(byte[] input, SecretKeySpec sm4Key, Cipher cipher, IvParameterSpec ivParameterSpec, int mode) throws InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        if (ivParameterSpec == null) {
            cipher.init(mode, sm4Key);
        } else {
            cipher.init(mode, sm4Key, ivParameterSpec);
        }
        return cipher.doFinal(input);
    }

    /**
     * SM4算法目前只支持128位（即密钥16字节）
     */
    public static final int DEFAULT_KEY_SIZE = 128;

    public byte[] generateKey() {
        kg.init(DEFAULT_KEY_SIZE, new SecureRandom());
        return kg.generateKey().getEncoded();
    }
}
