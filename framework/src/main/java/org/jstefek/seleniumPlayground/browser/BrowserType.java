package org.jstefek.seleniumPlayground.browser;

public enum BrowserType {

    FIREFOX("(?i)(firefox|ff)"), CHROME("(?i)(chrome|cr)"), UNKNOWN("");

    public static BrowserType parseBrowser(String s) {
        for (BrowserType value : values()) {
            if (!(value.matcher == null || value.matcher.isEmpty()) && s.matches(value.matcher)) {
                return value;
            }
        }
        return UNKNOWN;
    }

    private final String matcher;

    private BrowserType(String matcher) {
        this.matcher = matcher;
    }

}
