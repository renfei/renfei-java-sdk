package net.renfei.sdk.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * String工具类
 *
 * @author RenFei
 */
public class StringUtils {
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private static final String CHINA_PHONE_ALL = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[35-8]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[0135-9]\\d{2}|66\\d{2})\\d{6}$";
    private static final String CHINA_PHONE_CHINA_MOBILE = "^(?:\\+?86)?1(?:3(?:4[^9\\D]|[5-9]\\d)|5[^3-6\\D]\\d|8[23478]\\d|[79]8\\d)\\d{7}$";
    private static final String CHINA_PHONE_CHINA_UNICOM = "^(?:\\+?86)?1(?:3[0-2]|[578][56]|66)\\d{8}$";
    private static final String CHINA_PHONE_CHINA_TELECOM = "^(?:\\+?86)?1(?:3(?:3\\d|49)\\d|53\\d{2}|8[019]\\d{2}|7(?:[37]\\d{2}|40[0-5])|9[139]\\d{2})\\d{6}$";
    private static final String CHINA_PHONE_MVNO_ALL = "^(?:\\+?86)?1(?:7[01]|6[257])\\d{8}$";

    /**
     * 判断是否是邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (BeanUtils.isEmpty(email)) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }

    /**
     * 判断是否是中国手机号
     *
     * @param phone
     * @return
     */
    public static boolean isChinaPhone(String phone) {
        if (BeanUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches(CHINA_PHONE_ALL);
    }

    /**
     * 判断是否是中国移动手机号
     *
     * @param phone
     * @return
     */
    public static boolean isChinaMobilePhone(String phone) {
        if (BeanUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches(CHINA_PHONE_CHINA_MOBILE);
    }

    /**
     * 判断是否是中国联通手机号
     *
     * @param phone
     * @return
     */
    public static boolean isChinaUnicomePhone(String phone) {
        if (BeanUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches(CHINA_PHONE_CHINA_UNICOM);
    }

    /**
     * 判断是否是中国电信手机号
     *
     * @param phone
     * @return
     */
    public static boolean isChinaTelecomPhone(String phone) {
        if (BeanUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches(CHINA_PHONE_CHINA_TELECOM);
    }

    /**
     * 判断是否是中国虚拟运营商手机号
     *
     * @param phone
     * @return
     */
    public static boolean isChinaMvnoPhone(String phone) {
        if (BeanUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches(CHINA_PHONE_MVNO_ALL);
    }

    /**
     * 签名，先进行字典排序，再进行SHA1
     *
     * @param arr 参与签名的值
     * @return
     */
    public static String signature(String... arr) {
        if (BeanUtils.isEmpty(arr)) {
            return null;
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        //将参数拼接成一个字符串进行sha1加密
        for (String param : arr) {
            sb.append(param);
        }
        return EncryptionUtils.encrypt("SHA1", sb.toString());
    }

    /**
     * 获取指定长度随机字符串
     *
     * @param length 长度
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append((char) result);
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append((char) result);
                    break;
                case 2:
                    sb.append(new Random().nextInt(10));
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 获取指定位数的随机数字串
     *
     * @param length 长度
     * @return
     */
    public static String getRandomNumber(int length) {
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < length; i++) {
            rs.append(r.nextInt(10));
        }
        return rs.toString();
    }
}
