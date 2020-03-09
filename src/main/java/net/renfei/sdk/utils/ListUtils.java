package net.renfei.sdk.utils;

import java.util.List;

public class ListUtils {
    public static <E> E getOne(List<E> list) {
        if (BeanUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
}
