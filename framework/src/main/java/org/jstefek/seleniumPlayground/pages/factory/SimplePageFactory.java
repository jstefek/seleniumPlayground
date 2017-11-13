package org.jstefek.seleniumPlayground.pages.factory;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.AnnotationBasedRelocationChecker;
import org.jstefek.seleniumPlayground.pages.checker.PageLoadedChecker;
import org.jstefek.seleniumPlayground.pages.checker.PageRelocationChecker;
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
    private final PageRelocationChecker pageRelocationChecker;

    public SimplePageFactory() {
        this(new SimplePageInitializer(), new SimplePageInstanciator(), new VisibilityCheckingPageLoadedChecker(), new AnnotationBasedRelocationChecker());
    }

    SimplePageFactory(PageInitializer pageInitializer, PageInstanciator pageInstanciator, PageLoadedChecker pageLoadedChecker, PageRelocationChecker pageRelocationChecker) {
        this.pageInitializer = pageInitializer;
        this.pageInstanciator = pageInstanciator;
        this.pageLoadedChecker = pageLoadedChecker;
        this.pageRelocationChecker = pageRelocationChecker;
    }

    @Override
    public <T extends AbstractPage> T initializePage(Class<T> pageKlass, WebDriver browser) {
        T page = pageInstanciator.instantiatePage(pageKlass, browser, this);
        pageInitializer.initializePage(page, browser);
        pageLoadedChecker.waitForPageToLoad(page);
        pageRelocationChecker.checkPageRelocated(page);
        return page;
    }

}
