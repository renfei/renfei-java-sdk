package net.renfei.sdk.test.utils;

import net.renfei.sdk.utils.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 字符串工具测试
 *
 * @author RenFei
 */
public class StringUtilsTest {
    @Test
    public void emailFormatTest() {
        Assert.assertFalse(StringUtils.isEmail("w#test.com"));
        Assert.assertFalse(StringUtils.isEmail("ab@cn"));
        Assert.assertFalse(StringUtils.isEmail("ab@cn.*.com"));
        Assert.assertFalse(StringUtils.isEmail("ab##@test.com"));
        Assert.assertTrue(StringUtils.isEmail("test.test@test-test.com"));
        Assert.assertTrue(StringUtils.isEmail("test.test@test.test.com"));
        Assert.assertNull(StringUtils.signature());
        Assert.assertNotNull(StringUtils.signature("test", "ttt", "eee", "ttt"));
    }
}
