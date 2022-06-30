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
import net.renfei.sdk.utils.ChinaIdCardUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author renfei
 */
public class ChinaIdCardUtilsTest extends Tests {
    @Test
    public void validateCard() {
        assertTrue(ChinaIdCardUtils.validateCard("110101199003071057"));
        assertFalse(ChinaIdCardUtils.validateCard("110101199103071057"));
        assertEquals(ChinaIdCardUtils.getBirthByIdCard("110101199003071057"), "19900307");
        assertEquals(ChinaIdCardUtils.getYearByIdCard("110101199003071057"), Short.valueOf("1990"));
        assertEquals(ChinaIdCardUtils.getMonthByIdCard("110101199003071057"), Short.valueOf("03"));
        assertEquals(ChinaIdCardUtils.getDateByIdCard("110101199003071057"), Short.valueOf("07"));
        assertEquals(ChinaIdCardUtils.getGenderByIdCard("110101199003071057"), "M");
        assertEquals(ChinaIdCardUtils.getProvinceByIdCard("110101199003071057"), "北京");
    }
}
