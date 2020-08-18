package net.renfei.sdk.test.utils;

import net.renfei.sdk.utils.ChinaIdCardUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <p>Title: ChinaIdCardUtilsTest</p>
 * <p>Description: 身份证工具单元测试</p>
 *
 * @author RenFei
 * @date : 2020-08-18 22:42
 */
public class ChinaIdCardUtilsTest {
    @Test
    public void validateCard() {
        Assertions.assertTrue(ChinaIdCardUtils.validateCard("110101199003071057"));
        Assertions.assertFalse(ChinaIdCardUtils.validateCard("110101199103071057"));
        Assertions.assertEquals(ChinaIdCardUtils.getBirthByIdCard("110101199003071057"), "19900307");
        Assertions.assertEquals(ChinaIdCardUtils.getYearByIdCard("110101199003071057"), Short.valueOf("1990"));
        Assertions.assertEquals(ChinaIdCardUtils.getMonthByIdCard("110101199003071057"), Short.valueOf("03"));
        Assertions.assertEquals(ChinaIdCardUtils.getDateByIdCard("110101199003071057"), Short.valueOf("07"));
        Assertions.assertEquals(ChinaIdCardUtils.getGenderByIdCard("110101199003071057"), "M");
        Assertions.assertEquals(ChinaIdCardUtils.getProvinceByIdCard("110101199003071057"), "北京");
    }
}
