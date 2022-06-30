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
import net.renfei.sdk.utils.NumberUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author renfei
 */
public class NumberUtilsTest extends Tests {
    @Test
    public void numberUtilsTest() {
        assertEquals(NumberUtils.parseInt("123", 456), 123);
        assertEquals(NumberUtils.parseInt("test", 456), 456);
        assertEquals(NumberUtils.parseFloat("123.123", 456.456f), 123.123f);
        assertEquals(NumberUtils.parseFloat("test", 456.456f), 456.456f);
        assertEquals(NumberUtils.parseDouble("3.141592654", 1.23456f), 3.141592654d);
        assertEquals(NumberUtils.parseDouble("test", 1.23456d), 1.23456d);
    }
}
