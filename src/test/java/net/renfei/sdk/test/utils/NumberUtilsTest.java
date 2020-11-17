package net.renfei.sdk.test.utils;

import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <p>Title: NumberUtilsTest</p>
 * <p>Description: </p>
 *
 * @author RenFei
 */
public class NumberUtilsTest extends Tests {
    @Test
    public void numberUtilsTest() throws InterruptedException {
        Runnable taskTemp = () -> {
            Assertions.assertEquals(NumberUtils.parseInt("123", 456), 123);
            Assertions.assertEquals(NumberUtils.parseInt("test", 456), 456);
            Assertions.assertEquals(NumberUtils.parseFloat("123.123", 456.456f), 123.123f);
            Assertions.assertEquals(NumberUtils.parseFloat("test", 456.456f), 456.456f);
            Assertions.assertEquals(NumberUtils.parseDouble("3.141592654", 1.23456f), 3.141592654d);
            Assertions.assertEquals(NumberUtils.parseDouble("test", 1.23456d), 1.23456d);
        };
        startTaskAllInOnce(100, taskTemp);
    }
}
