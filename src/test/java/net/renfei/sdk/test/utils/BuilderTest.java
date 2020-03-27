package net.renfei.sdk.test.utils;

import net.renfei.sdk.entity.IpInfo;
import net.renfei.sdk.test.TestApplication;
import net.renfei.sdk.utils.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/**
 * @author RenFei
 */
public class BuilderTest extends TestApplication {
    @Test
    public void test() {
        System.out.println("==== " + this.getClass().getName() + " ====");
        IpInfo ipInfo = Builder.of(IpInfo::new)
                .with(IpInfo::setIp, "ip")
                .with(IpInfo::setIpBigInt, BigInteger.valueOf(1234567))
                .with(IpInfo::setCountryCode, "")
                .with(IpInfo::setCountryName, "")
                .with(IpInfo::setRegionName, "")
                .with(IpInfo::setCityName, "")
                .with(IpInfo::setLatitude, 1234D)
                .with(IpInfo::setLongitude, 1234D)
                .with(IpInfo::setZipCode, "")
                .with(IpInfo::setTimeZone, "")
                .build();
        Assertions.assertNotNull(ipInfo);
    }
}
