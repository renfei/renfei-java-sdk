package net.renfei.sdk.test.utils;

import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.test.TestApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author RenFei
 */
public class APIResultTest extends TestApplication {
    @Test
    public void test() {
        System.out.println("==== " + this.getClass().getName() + " ====");
        Assertions.assertNotNull(APIResult.success());
        Assertions.assertNotNull(APIResult.builder()
                .code(StateCode.OK)
                .message("test")
                .data("test")
                .build().toString());
    }
}
