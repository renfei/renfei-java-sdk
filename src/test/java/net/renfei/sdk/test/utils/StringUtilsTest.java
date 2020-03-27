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
        Assertions.assertFalse(StringUtils.isEmail("w#test.com"));
        Assertions.assertFalse(StringUtils.isEmail("ab@cn"));
        Assertions.assertFalse(StringUtils.isEmail("ab@cn.*.com"));
        Assertions.assertFalse(StringUtils.isEmail("ab##@test.com"));
        Assertions.assertTrue(StringUtils.isEmail("test.test@test-test.com"));
        Assertions.assertTrue(StringUtils.isEmail("test.test@test.test.com"));
        Assertions.assertTrue(StringUtils.isChinaPhone("13001001234"));
        Assertions.assertTrue(StringUtils.isChinaMobilePhone("13901001234"));
        Assertions.assertTrue(StringUtils.isChinaUnicomePhone("13001001234"));
        Assertions.assertTrue(StringUtils.isChinaTelecomPhone("18901001234"));
        Assertions.assertTrue(StringUtils.isChinaMvnoPhone("17001001234"));
        Assertions.assertNull(StringUtils.signature());
        Assertions.assertNotNull(StringUtils.signature("test", "ttt", "eee", "ttt"));
        Assertions.assertNotNull(StringUtils.getRandomString(12));
        Assertions.assertNotNull(StringUtils.getRandomNumber(12));
        Assertions.assertNotNull(RandomStringUtils.getRandomString(12));
    }
}
