package org.jstefek.seleniumPlayground.browser.factory;

import org.jstefek.seleniumPlayground.browser.BrowserType;
import org.openqa.selenium.WebDriver;

public interface BrowserFactory {

    /**
     * @param t type of browser
     * @return new instance of browser with given type or throws UnsupportedOperationException when the browser type is unknown
     */
    WebDriver startBrowser(BrowserType t);
}
