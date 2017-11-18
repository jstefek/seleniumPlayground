package org.jstefek.seleniumPlayground.pages.utils;

/**
 * String utilities
 */
class SimpleStringUtilsService implements StringUtilsService {

    @Override
    public boolean isNotNullNorEmpty(String s) {
        return !isNullOrEmpty(s);
    }

    @Override
    public boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

}
