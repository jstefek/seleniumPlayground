package org.jstefek.seleniumPlayground.pages;

import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.jstefek.seleniumPlayground.config.BaseConfig;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.jstefek.seleniumPlayground.pages.utils.ajax.RequestGuard;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    private final PageConfiguration config;

    @Inject
    public AbstractPage(PageConfiguration config) {
        this.config = config;
    }

    /**
     * @return new FluentWait with predefined wait time, polling time and ignored exceptions
     */
    public FluentWait<WebDriver> createWait() {
        return new WebDriverWait(getBrowser(), BaseConfig.INSTANCE.getBaseWaitTime().getValue(TimeUnit.SECONDS))
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
        return config.getPageFactory().initializePage(pageKlass);
    }

    /**
     * @return instance of actually used WebDriver
     */
    protected WebDriver getBrowser() {
        return config.getBrowser();
    }

    /**
     * @return instance of actually used JavascriptExecutor
     */
    protected JavascriptExecutor getExecutor() {
        return (JavascriptExecutor) getBrowser();
    }

    /**
     * @return instance of actually used RequestGuard
     */
    protected RequestGuard getRequestGuard() {
        return config.getRequestGuard();
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
