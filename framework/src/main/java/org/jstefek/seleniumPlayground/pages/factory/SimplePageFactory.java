package org.jstefek.seleniumPlayground.pages.factory;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.PageLoadedChecker;
import org.jstefek.seleniumPlayground.pages.checker.VisibilityCheckingPageLoadedChecker;
import org.jstefek.seleniumPlayground.pages.instanciator.PageInstanciator;
import org.jstefek.seleniumPlayground.pages.instanciator.SimplePageInstanciator;
import org.jstefek.seleniumPlayground.pages.intialize.PageInitializer;
import org.jstefek.seleniumPlayground.pages.intialize.SimplePageInitializer;
import org.openqa.selenium.WebDriver;

public class SimplePageFactory implements PageFactory {

    private final PageInitializer pageInitializer;
    private final PageInstanciator pageInstanciator;
    private final PageLoadedChecker pageLoadedChecker;

    public SimplePageFactory() {
        this(new VisibilityCheckingPageLoadedChecker(), new SimplePageInitializer(), new SimplePageInstanciator());
    }

    public SimplePageFactory(PageLoadedChecker pageLoadedChecker, PageInitializer pageInitializer, PageInstanciator pageInstanciator) {
        this.pageLoadedChecker = pageLoadedChecker;
        this.pageInitializer = pageInitializer;
        this.pageInstanciator = pageInstanciator;
    }

    @Override
    public <T extends AbstractPage> T initializePage(Class<T> pageKlass, WebDriver browser) {
        T page = pageInstanciator.instantiatePage(pageKlass, browser, this);
        pageInitializer.initializePage(page, browser);
        pageLoadedChecker.waitForPageToLoad(page);
        return page;
    }

}
