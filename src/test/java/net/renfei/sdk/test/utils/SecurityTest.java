package net.renfei.sdk.test.utils;

import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.SecurityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <p>Title: SecurityTest</p>
 * <p>Description: </p>
 *
 * @author RenFei
 * @date : 2020-09-26 21:17
 */
public class SecurityTest extends Tests {
    @Test
    public void testCheckSqlInjection() {
        boolean b1 = SecurityUtils.containsSqlInjection("and nm=1");
        Assertions.assertTrue(b1);
        boolean b2 = SecurityUtils.containsSqlInjection("niamsh delete from ");
        Assertions.assertTrue(b2);
        boolean b3 = SecurityUtils.containsSqlInjection("stand");
        Assertions.assertFalse(b3);
        boolean b4 = SecurityUtils.containsSqlInjection("and");
        Assertions.assertTrue(b4);
        boolean b5 = SecurityUtils.containsSqlInjection("niasdm%asjdj");
        Assertions.assertTrue(b5);
    }
}
