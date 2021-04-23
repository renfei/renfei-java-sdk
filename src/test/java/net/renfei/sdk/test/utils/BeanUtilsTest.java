package net.renfei.sdk.test.utils;

import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.BeanUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author RenFei
 */
public class BeanUtilsTest extends Tests {
    @Test
    public void testBeanUtils() {
        System.out.println("==== " + this.getClass().getName() + " ====");
        Object nullObj = null;
        String nullString = null;
        String emptyString = "";
        String notNullString = "test";
        Map nullMap = null;
        Map emptyMap = new HashMap();
        Map notNullMap = new HashMap() {{
            put("test", "ttt");
        }};

        String[] nullStringArr = null;
        String[] emptyStringArr = new String[]{};
        String[] notNullStringArr = new String[]{
                "test"
        };
        Optional nullOptional = null;
        Optional notNullOptional = Optional.of("test");

        Assertions.assertTrue(BeanUtils.isEmpty(nullObj));
        Assertions.assertTrue(BeanUtils.isEmpty(nullString));
        Assertions.assertTrue(BeanUtils.isEmpty(emptyString));
        Assertions.assertFalse(BeanUtils.isEmpty(notNullString));

        Assertions.assertTrue(BeanUtils.isEmpty(nullMap));
        Assertions.assertTrue(BeanUtils.isEmpty(emptyMap));
        Assertions.assertFalse(BeanUtils.isEmpty(notNullMap));

        Assertions.assertTrue(BeanUtils.isEmpty(nullStringArr));
        Assertions.assertTrue(BeanUtils.isEmpty(emptyStringArr));
        Assertions.assertFalse(BeanUtils.isEmpty(notNullStringArr));

        Assertions.assertTrue(BeanUtils.isEmpty(nullOptional));
        Assertions.assertFalse(BeanUtils.isEmpty(notNullOptional));

        String test = "";
        Assertions.assertEquals("default", BeanUtils.isEmpty(test, "default"));
    }
}
