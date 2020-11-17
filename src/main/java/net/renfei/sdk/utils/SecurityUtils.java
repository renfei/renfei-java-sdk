package net.renfei.sdk.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: SecurityUtils</p>
 * <p>Description: 安全工具类</p>
 *
 * @author RenFei
 * @date : 2020-09-26 21:14
 */
public class SecurityUtils {
    public final static Pattern SQL_CHECK_PATTERN =
            Pattern.compile("\\b(and|exec|insert|select|drop|grant|alter|delete|update|count|chr|mid|master|truncate|char|declare|or)\\b|(\\*|;|\\+|'|%)");

    /**
     * 是否含有sql注入，返回true表示含有
     *
     * @param obj
     * @return
     */
    public static boolean containsSqlInjection(Object obj) {
        Matcher matcher = SQL_CHECK_PATTERN.matcher(obj.toString());
        return matcher.find();
    }
}
