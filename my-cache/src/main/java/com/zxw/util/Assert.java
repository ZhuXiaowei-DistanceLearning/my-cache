package com.zxw.util;

/**
 * @author zxw
 * @date 2020/5/31 11:09
 */
public abstract class Assert {

    public static void isNull(Object object, String msg) {
        if (object == null) {
            throw new IllegalArgumentException(msg);
        }
    }
}
