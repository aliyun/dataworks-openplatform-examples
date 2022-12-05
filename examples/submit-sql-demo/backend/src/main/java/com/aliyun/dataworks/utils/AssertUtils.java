package com.aliyun.dataworks.utils;


import java.util.Collection;

/**
 * @author dataworks demo
 */
public class AssertUtils {


    /**
     * if not null then pass,or throw {@link RuntimeException}
     * @param object
     * @param paramName
     */
    public static void assertNotNull(Object object, String paramName) {
        if (object == null) {
            throw new RuntimeException("the ["+ paramName+"] can't be null value.");
        }
    }

    /**
     * @param object
     * @param paramName
     */
    public static void assertNotEmpty(Collection<?> object, String paramName) {
        assertNotNull(object, paramName);
        if (object.isEmpty()) {
            throw new RuntimeException("the ["+ paramName+"] can't be an empty list.");
        }
    }

    /**
     * condition equals true then pass, or throw {@link RuntimeException}
     * @param condition
     * @param errorMessage
     */
    public static void assertTrue(Boolean condition, String errorMessage) {
        if (!condition) {
            throw new RuntimeException(errorMessage);
        }
    }


}
