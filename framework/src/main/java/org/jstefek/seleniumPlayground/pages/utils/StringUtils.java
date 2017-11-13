package org.jstefek.seleniumPlayground.pages.utils;

/**
 * String utilities
 */
public final class StringUtils {

    public static boolean isNotNullNorEmpty(String s) {
        return !isNullOrEmpty(s);
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    private StringUtils() {
        throw new IllegalStateException("Cannot create utility class");
    }
}
