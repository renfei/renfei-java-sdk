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
 * 国产商密基类
 *
 * @author renfei
 */
public abstract class AbstractSM {
    protected static final String EC_VALUE = "EC";
    protected static final String SM3SM2_VALUE = "SM3WITHSM2";
    protected static final String CURVE_NAME = "sm2p256v1";
    protected static final String SM2 = "sm2";
    protected static final String SM3 = "sm3";
    protected static final String SM4 = "sm4";
}
