package net.renfei.sdk.test.utils;

import net.renfei.sdk.entity.IpInfo;
import net.renfei.sdk.utils.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/**
 * @author RenFei
 */
public class BuilderTest {
    @Test
    public void testBuilder() {
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
        System.out.println(ipInfo.getCityName());
        System.out.println(ipInfo.getCountryCode());
        System.out.println(ipInfo.getCountryName());
        System.out.println(ipInfo.getIp());
        System.out.println(ipInfo.getIpBigInt());
        System.out.println(ipInfo.getLatitude());
        System.out.println(ipInfo.getLongitude());
        System.out.println(ipInfo.getRegionName());
        System.out.println(ipInfo.getTimeZone());
        System.out.println(ipInfo.getZipCode());
        Demo demo = Builder.of(Demo::new)
                .with(Demo::setDemo, "Test")
                .with(Demo::setDemo, "Test","Test")
                .with(Demo::setDemo, "Test","Test","Test")
                .with(Demo::setDemo, "Test","Test","Test","Test")
                .with(Demo::setDemo, "Test","Test","Test","Test","Test")
                .build();
        Assertions.assertNotNull(demo);
    }

    public class Demo {
        public void setDemo(String p1) {
        }

        public void setDemo(String p1, String p2) {
        }

        public void setDemo(String p1, String p2, String p3) {
        }

        public void setDemo(String p1, String p2, String p3, String p4) {
        }

        public void setDemo(String p1, String p2, String p3, String p4, String p5) {
        }
    }
}
