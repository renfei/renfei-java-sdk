package net.renfei.sdk.test.utils;

import net.renfei.sdk.utils.DateUtils;
import org.junit.Test;

/**
 * @author RenFei
 */
public class DateUtilsTest {
    @Test
    public void test(){
        System.out.println(DateUtils.getDateTime());
        System.out.println(DateUtils.getDate(DateUtils.nextHours(-1),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.getDate(DateUtils.nextMinutes(-1),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.getDate(DateUtils.nextDay(-1),"yyyy-MM-dd HH:mm:ss"));
    }
}
