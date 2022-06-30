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

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author renfei
 */
public class SM4Cipher {
    private final Map<SM4ModeAndPaddingEnum, Cipher> cipherMap = new EnumMap<>(SM4ModeAndPaddingEnum.class);

    public SM4Cipher() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        for (SM4ModeAndPaddingEnum mode : SM4ModeAndPaddingEnum.values()) {
            Cipher cipher = Cipher.getInstance(mode.getName(), BouncyCastleProvider.PROVIDER_NAME);
            cipherMap.put(mode, cipher);
        }
    }

    public Cipher getCipher(SM4ModeAndPaddingEnum sm4ModeAndPaddingEnum) {
        return cipherMap.get(sm4ModeAndPaddingEnum);
    }
}
