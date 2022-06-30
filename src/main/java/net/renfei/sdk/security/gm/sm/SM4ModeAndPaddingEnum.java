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

/**
 * @author renfei
 */
public enum SM4ModeAndPaddingEnum {
    SM4_ECB_NoPadding("SM4/ECB/NoPadding"),
    SM4_ECB_PKCS5Padding("SM4/ECB/PKCS5Padding"),
    SM4_ECB_PKCS7Padding("SM4/ECB/PKCS7Padding"),
    SM4_CBC_NoPadding("SM4/CBC/NoPadding"),
    SM4_CBC_PKCS5Padding("SM4/CBC/PKCS5Padding"),
    SM4_CBC_PKCS7Padding("SM4/CBC/PKCS7Padding"),
    // CFB,OFB,CTR三种模式无需填充(padding)
    SM4_CFB_NoPadding("SM4/CFB/NoPadding"),
    SM4_OFB_NoPadding("SM4/OFB/NoPadding"),
    SM4_CTR_NoPadding("SM4/CTR/NoPadding");

    private final String name;

    SM4ModeAndPaddingEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
