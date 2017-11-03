package org.jstefek.seleniumPlayground.pages.intialize;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

/**
 * Initializes page using default Selenium's method of PageFactory
 * {@link org.openqa.selenium.support.PageFactory#initElements(WebDriver, Object) PageFactory.initElements(browser, page)}.
 */
public class SimplePageInitializer implements PageInitializer {

    @Override
    public <T extends AbstractPage> void initializePage(T page, WebDriver browser) {
        org.openqa.selenium.support.PageFactory.initElements(browser, page);
    }
}
