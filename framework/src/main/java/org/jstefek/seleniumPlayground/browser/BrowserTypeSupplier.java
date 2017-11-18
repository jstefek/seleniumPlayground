package org.jstefek.seleniumPlayground.browser;

import java.util.function.Supplier;

class BrowserTypeSupplier implements Supplier<BrowserType> {

    private static final String BROWSER_SYS_PROPERTY = "browser";

    @Override
    public BrowserType get() {
        return BrowserType.parseBrowser(System.getProperty(BROWSER_SYS_PROPERTY));
    }

}
