package net.renfei.sdk.test.utils;

import net.renfei.sdk.utils.EncryptionUtils;
import org.junit.jupiter.api.Test;

/**
 * @author RenFei
 */
public class EncryptionUtilsTest {
    @Test
    public void testEncryptionUtils() {
        System.out.println("==== " + this.getClass().getName() + " ====");
        System.out.println(EncryptionUtils.encrypt("SHA1", "Test"));
        System.out.println(EncryptionUtils.encrypt("MD5", "Test"));
    }
}
