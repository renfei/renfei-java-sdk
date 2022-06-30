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

import net.renfei.sdk.security.SHAUtil;
import net.renfei.sdk.test.Tests;
import org.junit.jupiter.api.Test;

/**
 * @author renfei
 */
public class SHAUtilTest extends Tests {
    @Test
    public void test() {
        String msg = "dsdgsaf";
        System.out.println(SHAUtil.md516(msg));
        System.out.println(SHAUtil.md5(msg));
        System.out.println(SHAUtil.sha1(msg));
        System.out.println(SHAUtil.sha256(msg));
        System.out.println(SHAUtil.sha512(msg));
    }
}
