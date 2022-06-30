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
import net.renfei.sdk.utils.PasswordUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author renfei
 */
public class PasswordUtilsTest extends Tests {
    @Test
    public void testPasswordUtils() throws PasswordUtils.CannotPerformOperationException {
        System.out.println("==== " + this.getClass().getName() + " ====");
        String password = "MyPassword", correctHash;
        correctHash = PasswordUtils.createHash(password);
        System.out.println(correctHash);
        assertTrue(PasswordUtils.verifyPassword(password, correctHash));
        assertFalse(PasswordUtils.verifyPassword(password, ""));
        assertFalse(PasswordUtils.verifyPassword(password, "test:test:test"));

        correctHash = PasswordUtils.createHash(password,"sha1");
        System.out.println(correctHash);
        assertTrue(PasswordUtils.verifyPassword(password, correctHash));
        assertFalse(PasswordUtils.verifyPassword(password, ""));
        assertFalse(PasswordUtils.verifyPassword(password, "test:test:test"));

        correctHash = PasswordUtils.createHash(password,"sm3");
        System.out.println(correctHash);
        assertTrue(PasswordUtils.verifyPassword(password, correctHash));
        assertFalse(PasswordUtils.verifyPassword(password, ""));
        assertFalse(PasswordUtils.verifyPassword(password, "test:test:test"));
    }
}
