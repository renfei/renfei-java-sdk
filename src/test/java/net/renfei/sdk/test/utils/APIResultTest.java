package net.renfei.sdk.test.utils;

import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author RenFei
 */
public class APIResultTest {
    @Test
    public void testAPIResult() {
        System.out.println("==== " + this.getClass().getName() + " ====");
        Assertions.assertNotNull(APIResult.success());
        APIResult apiResult = APIResult.builder()
                .code(StateCode.OK)
                .message("test")
                .data("test")
                .build();
        Assertions.assertNotNull(apiResult);
        System.out.println(apiResult.getCode());
        System.out.println(apiResult.getMessage());
        System.out.println(apiResult.getNonce());
        System.out.println(apiResult.getSignature());
        System.out.println(apiResult.getTimestamp());
        System.out.println(apiResult.getData());
    }
}
