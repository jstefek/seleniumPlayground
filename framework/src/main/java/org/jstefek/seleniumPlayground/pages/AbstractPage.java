package org.jstefek.seleniumPlayground.pages;

import java.util.concurrent.TimeUnit;
import org.jstefek.seleniumPlayground.config.BaseConfig;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.jstefek.seleniumPlayground.pages.utils.ajax.RequestGuard;
import org.jstefek.seleniumPlayground.pages.utils.ajax.SimpleRequestGuard;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    private final WebDriver browser;
    private final PageFactory pageFactory;
    private final RequestGuard requestGuard;

    public AbstractPage(WebDriver browser, PageFactory pageFactory) {
        this.browser = browser;
        this.pageFactory = pageFactory;
        this.requestGuard = new SimpleRequestGuard(browser);
    }

    /**
     * @return new FluentWait with predefined wait time, polling time and ignored exceptions
     */
    public FluentWait<WebDriver> createWait() {
        return new WebDriverWait(browser, BaseConfig.INSTANCE.getBaseWaitTime().getValue(TimeUnit.SECONDS))
                .pollingEvery(BaseConfig.INSTANCE.getPollingCycleTime().getValue(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    /**
     * Waits for page to load. Uses method {@link #isLoaded isLoaded} for checking the condition.
     */
    public void waitForLoad() {
        createWait()
                .withTimeout(BaseConfig.INSTANCE.getPageLoadWaitTime().getValue(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS)
                .withMessage("page to be loaded")
                .until(d -> isLoaded());
    }

    /**
     * @param <T>
     * @param pageKlass
     * @return new initialized page object of given class, shortcut for {@link PageFactory#initializePage initializePage}
     */
    protected <T extends AbstractPage> T createPage(Class<T> pageKlass) {
        return pageFactory.initializePage(pageKlass, browser);
    }

    /**
     * @return instance of actually used WebDriver
     */
    protected WebDriver getBrowser() {
        return browser;
    }

    /**
     * @return instance of actually used JavascriptExecutor
     */
    protected JavascriptExecutor getExecutor() {
        return (JavascriptExecutor) browser;
    }

    /**
     * @return instance of actually used RequestGuard
     */
    protected RequestGuard getRequestGuard() {
        return requestGuard;
    }

    /**
     * @return true when page is loaded, false otherwise
     */
    protected boolean isLoaded() {
        return Boolean.valueOf(
                getExecutor().executeScript("return document.readyState=='complete'").toString()
        );
    }

}
