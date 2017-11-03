package org.jstefek.seleniumPlayground.pages.intialize;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

/**
 * Initializer of page objects, creates proxies for all WebElement fields using theirs @FindBy to find them.
 */
public interface PageInitializer {

    /**
     * Initializes page object, creating proxies for all WebElement fields.
     *
     * @param <T> type of page
     * @param page page instance
     * @param browser WebDriver instance
     */
    <T extends AbstractPage> void initializePage(T page, WebDriver browser);

}
