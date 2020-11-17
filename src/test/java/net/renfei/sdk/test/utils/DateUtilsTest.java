package net.renfei.sdk.test.utils;

import lombok.SneakyThrows;
import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author RenFei
 */
public class DateUtilsTest extends Tests {
    @Test
    public void testDateUtils() throws InterruptedException {
        Runnable taskTemp = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("==== " + this.getClass().getName() + " ====");
                System.out.println(DateUtils.getDate());
                System.out.println(DateUtils.getTime());
                System.out.println(DateUtils.getDateTime());
                System.out.println(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
                System.out.println(DateUtils.getDate(DateUtils.nextHours(-1), "yyyy-MM-dd HH:mm:ss"));
                System.out.println(DateUtils.getDate(DateUtils.nextMinutes(-1), "yyyy-MM-dd HH:mm:ss"));
                System.out.println(DateUtils.getDate(DateUtils.nextDay(-1), "yyyy-MM-dd HH:mm:ss"));
                System.out.println(DateUtils.getDate(DateUtils.nextMonth(1), "yyyy-MM-dd HH:mm:ss"));
                System.out.println(DateUtils.getDate(DateUtils.nextYear(-1), "yyyy-MM-dd HH:mm:ss"));
                System.out.println(DateUtils.getYear());
                System.out.println(DateUtils.getMonth());
                System.out.println(DateUtils.getDay());
                System.out.println(DateUtils.getWeek());
                System.out.println(DateUtils.pastDays(DateUtils.parseDate("2020-12-24")));
                System.out.println(DateUtils.getCalendar(DateUtils.parseDate("2020-12-24")));
                System.out.println(DateUtils.getWeeksBetween(DateUtils.parseDate("2020-12-24"), new Date()));
                System.out.println(DateUtils.getSpecifiedDayAfter("2020-12-24", 1));
                System.out.println(DateUtils.dateMinus(DateUtils.parseDate("2020-12-24"), new Date()));
                System.out.println(DateUtils.getCurrentSeason());
                System.out.println(DateUtils.getIntervalBySeconds(1));
                System.out.println(DateUtils.getIntervalBySeconds(121));
                System.out.println(DateUtils.getIntervalBySeconds(12345));
                System.out.println(DateUtils.getIntervalBySeconds(1234567));
                System.out.println(DateUtils.getIntervalBySeconds(1234567890));
                System.out.println(DateUtils.getNowTimeBefore(1));
                System.out.println(DateUtils.getNowTimeBefore(121));
                System.out.println(DateUtils.getNowTimeBefore(12345));
                System.out.println(DateUtils.getNowTimeBefore(1234567));
                System.out.println(DateUtils.getNowTimeBefore(1234567890));
                System.out.println(DateUtils.getMonthsBetween("2020-12-24", "2020-12-31"));
                System.out.println(DateUtils.getDayOfWeek("2020-12-24"));
                System.out.println(DateUtils.snsFormat(30));
                System.out.println(DateUtils.snsFormat(3500));
                System.out.println(DateUtils.snsFormat(86401));
                System.out.println(DateUtils.snsFormat(2592001));
                System.out.println(DateUtils.getUnixTimestamp());
                Assertions.assertNotNull(DateUtils.formatDate(new Date(), null));
                Assertions.assertNull(DateUtils.parseDate(null));
                Assertions.assertNull(DateUtils.parseDate("test"));
                Assertions.assertNotNull(DateUtils.dateMinus(null, null));
            }
        };
        startTaskAllInOnce(100, taskTemp);
    }
}
