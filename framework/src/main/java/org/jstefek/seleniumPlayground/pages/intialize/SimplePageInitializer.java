package org.jstefek.seleniumPlayground.pages.intialize;

import javax.inject.Inject;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

/**
 * Initializes page using default Selenium's method of PageFactory
 * {@link org.openqa.selenium.support.PageFactory#initElements(WebDriver, Object) PageFactory.initElements(browser, page)}.
 */
class SimplePageInitializer implements PageInitializer {

    private final WebDriver browser;

    @Inject
    SimplePageInitializer(WebDriver browser) {
        this.browser = browser;
    }

    @Override
    public <T extends AbstractPage> void initializePage(T page) {
        org.openqa.selenium.support.PageFactory.initElements(browser, page);
    }
}
