package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import org.openqa.selenium.WebDriver;

public interface BrowserInstanciator {

    /**
     * @return new instance of WebDriver
     */
    WebDriver startBrowser();
}
