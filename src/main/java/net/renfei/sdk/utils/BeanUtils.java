package net.renfei.sdk.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

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
        } else if (o instanceof Optional) {
            return !((Optional) o).isPresent();
        }
        return false;
    }

    /**
     * 判断对象是否为空，为空就返回指定的默认对象
     *
     * @param object        需要判断的对象
     * @param defaultObject 为空时使用的默认对象
     * @param <T>           类型
     * @return 指定的类型的非空对象
     */
    public static <T> T isEmpty(T object, T defaultObject) {
        boolean isEmptyBoolean = isEmpty(object);
        if (isEmptyBoolean) {
            return defaultObject;
        } else {
            return object;
        }
    }
}
