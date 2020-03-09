package net.renfei.sdk.utils;

import java.util.Collection;
import java.util.Map;

/**
 * JavaBean工具
 *
 * @author RenFei
 */
public class BeanUtils {
    /**
     * 判断对象是否为空
     *
     * @param o java.lang.Object.
     * @return boolean.
     */
    @SuppressWarnings("unused")
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            return ((String) o).trim().length() == 0;
        } else if (o instanceof Collection) {
            return ((Collection) o).isEmpty();
        } else if (o.getClass().isArray()) {
            return ((Object[]) o).length == 0;
        } else if (o instanceof Map) {
            return ((Map) o).isEmpty();
        }
        return false;
    }
}
