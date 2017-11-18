package org.jstefek.seleniumPlayground.pages.factory;

import org.jstefek.seleniumPlayground.pages.AbstractPage;

public interface PageFactory {

    /**
     * Instantiates page object. Creates proxies for all fields of type WebElement. Waits for page to load.
     *
     * @param <T> type of page
     * @param pageKlass page class to be instantiated
     * @return initialized page object
     */
    <T extends AbstractPage> T initializePage(Class<T> pageKlass);
}
