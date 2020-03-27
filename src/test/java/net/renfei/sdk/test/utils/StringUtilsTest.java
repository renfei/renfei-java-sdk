package net.renfei.sdk.test.utils;

import net.renfei.sdk.test.TestApplication;
import net.renfei.sdk.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 字符串工具测试
 *
 * @author RenFei
 */
public class StringUtilsTest extends TestApplication {
    @Test
    public void emailFormatTest() {
        Assertions.assertFalse(StringUtils.isEmail("w#test.com"));
        Assertions.assertFalse(StringUtils.isEmail("ab@cn"));
        Assertions.assertFalse(StringUtils.isEmail("ab@cn.*.com"));
        Assertions.assertFalse(StringUtils.isEmail("ab##@test.com"));
        Assertions.assertTrue(StringUtils.isEmail("test.test@test-test.com"));
        Assertions.assertTrue(StringUtils.isEmail("test.test@test.test.com"));
        Assertions.assertNull(StringUtils.signature());
        Assertions.assertNotNull(StringUtils.signature("test", "ttt", "eee", "ttt"));
        Assertions.assertNotNull(StringUtils.getRandomString(12));
        Assertions.assertNotNull(StringUtils.getRandomNumber(12));
    }
}
