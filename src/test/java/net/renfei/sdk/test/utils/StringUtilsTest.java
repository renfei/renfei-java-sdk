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
import net.renfei.sdk.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/**
 * @author renfei
 */
public class StringUtilsTest extends Tests {
    @Test
    public void testStringUtils() {
        System.out.println("==== " + this.getClass().getName() + " ====");
        Assertions.assertFalse(StringUtils.isDomain(null));
        Assertions.assertFalse(StringUtils.isDomain("w#test.com"));
        Assertions.assertTrue(StringUtils.isDomain("www.renfei.net"));
        Assertions.assertFalse(StringUtils.isEmail(null));
        Assertions.assertFalse(StringUtils.isEmail("w#test.com"));
        Assertions.assertFalse(StringUtils.isEmail("ab@cn"));
        Assertions.assertFalse(StringUtils.isEmail("ab@cn.*.com"));
        Assertions.assertFalse(StringUtils.isEmail("ab##@test.com"));
        Assertions.assertTrue(StringUtils.isEmail("test.test@test-test.com"));
        Assertions.assertTrue(StringUtils.isEmail("test.test@test.test.com"));
        Assertions.assertFalse(StringUtils.isChinaPhone(null));
        Assertions.assertTrue(StringUtils.isChinaPhone("13001001234"));
        Assertions.assertFalse(StringUtils.isChinaMobilePhone(null));
        Assertions.assertTrue(StringUtils.isChinaMobilePhone("13901001234"));
        Assertions.assertFalse(StringUtils.isChinaUnicomePhone(null));
        Assertions.assertTrue(StringUtils.isChinaUnicomePhone("13001001234"));
        Assertions.assertFalse(StringUtils.isChinaTelecomPhone(null));
        Assertions.assertTrue(StringUtils.isChinaTelecomPhone("18901001234"));
        Assertions.assertTrue(StringUtils.isChinaMvnoPhone("17001001234"));
        Assertions.assertNull(StringUtils.signature());
        Assertions.assertNotNull(StringUtils.signature("test", "ttt", "eee", "ttt"));
        Assertions.assertNotNull(StringUtils.getRandomString(12));
        Assertions.assertNotNull(StringUtils.getRandomNumber(12));
        Assertions.assertNotNull(StringUtils.getRandomString(12));
        String str = "abcd{'a':'b'}";
        String encoded = StringUtils.encodeBase64(str.getBytes());
        byte[] decoded = StringUtils.decodeBase64(encoded);
        Assertions.assertEquals(str, new String(decoded));
        String utf8Encoded = StringUtils.encodeUTF8StringBase64(str);
        String utf8Decoded = StringUtils.decodeUTF8StringBase64(utf8Encoded);
        Assertions.assertEquals(str, utf8Decoded);
        String url = "== wo";
        String urlEncoded = StringUtils.encodeURL(url);
        String urlDecoded = StringUtils.decodeURL(urlEncoded);
        Assertions.assertEquals(url, urlDecoded);
        StringUtils.delHtmlTags("<html>lalala</html>");
        StringUtils.delHtmlTags("<html>lalala</html>");
        String ip = "8.8.8.8";
        BigInteger bigInteger = StringUtils.stringToBigInt(ip);
        Assertions.assertEquals(ip, StringUtils.bigIntToString(bigInteger));
        Assertions.assertEquals("test_test_test", StringUtils.humpToLine("testTestTest"));
        Assertions.assertEquals("testTestTest", StringUtils.lineToHump("test_test_test"));
    }
}
