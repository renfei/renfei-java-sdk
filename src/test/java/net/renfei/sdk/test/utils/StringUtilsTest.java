package net.renfei.sdk.test.utils;

import net.renfei.sdk.utils.RandomStringUtils;
import net.renfei.sdk.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 字符串工具测试
 *
 * @author RenFei
 */
public class StringUtilsTest {
    @Test
    public void testStringUtils() {
        System.out.println("==== " + this.getClass().getName() + " ====");
        Assertions.assertFalse(StringUtils.isDomain(null));
        Assertions.assertFalse(StringUtils.isDomain("w#test.com"));
        Assertions.assertTrue(StringUtils.isEmail("www.renfei.net"));
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
        Assertions.assertNotNull(RandomStringUtils.getRandomString(12));
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
    }
}
