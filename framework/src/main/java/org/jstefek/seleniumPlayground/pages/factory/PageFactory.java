package org.jstefek.seleniumPlayground.pages.factory;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public interface PageFactory {

    /**
     * Instantiates page object. Creates proxies for all fields of type WebElement. Waits for page to load.
     *
     * @param <T> type of page
     * @param pageKlass page class to be instantiated
     * @param browser instance of WebDriver, which will be used for finding elements
     * @return initialized page object
     */
    <T extends AbstractPage> T initializePage(Class<T> pageKlass, WebDriver browser);
}
