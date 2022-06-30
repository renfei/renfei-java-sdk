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
package net.renfei.sdk.test.utils;

import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.GoogleAuthenticator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author renfei
 */
public class GoogleAuthenticatorTest extends Tests {
    @Test
    public void testGoogleAuthenticator() throws InterruptedException {
        System.out.println("==== " + this.getClass().getName() + " ====");
        GoogleAuthenticator.setWindowSize(2);
        String secretKey = GoogleAuthenticator.generateSecretKey("abc123");
        String totp = GoogleAuthenticator.genTotpString("RENFEI.NET", "Tester", secretKey);
        System.out.println(totp);
        assertFalse(GoogleAuthenticator.authcode("123456", secretKey));
        assertFalse(GoogleAuthenticator.authcode("abc", secretKey));
    }
}
