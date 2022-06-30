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

import org.bouncycastle.crypto.digests.SM3Digest;

import java.util.Arrays;
import java.util.Base64;

/**
 * 散列算法
 *
 * @author renfei
 */
public class SM3Util extends AbstractSM {
    private SM3Util() {
        super();
    }

    /**
     * 计算SM3摘要值
     *
     * @param srcData 原文
     * @return 摘要值
     */
    public static String hash(String srcData) {
        return Base64.getEncoder().encodeToString(hash(srcData.getBytes()));
    }

    /**
     * SM3摘要值验证
     *
     * @param srcData    原文
     * @param sm3HashVal 原文对应的摘要值
     * @return 返回true标识验证成功，false标识验证失败
     */
    public static boolean verify(String srcData, String sm3HashVal) {
        return verify(srcData.getBytes(), Base64.getDecoder().decode(sm3HashVal));
    }

    /**
     * 计算SM3摘要值
     *
     * @param srcData 原文
     * @return 摘要值
     */
    public static byte[] hash(byte[] srcData) {
        final SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] hashVal = new byte[digest.getDigestSize()];
        digest.doFinal(hashVal, 0);
        return hashVal;
    }

    /**
     * SM3摘要值验证
     *
     * @param srcData    原文
     * @param sm3HashVal 原文对应的摘要值
     * @return 返回true标识验证成功，false标识验证失败
     */
    public static boolean verify(byte[] srcData, byte[] sm3HashVal) {
        byte[] hashVal = hash(srcData);
        return Arrays.equals(hashVal, sm3HashVal);
    }
}
