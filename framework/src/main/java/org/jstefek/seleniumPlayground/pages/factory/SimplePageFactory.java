package org.jstefek.seleniumPlayground.pages.factory;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.PageChecker;
import org.jstefek.seleniumPlayground.pages.checker.PageLoadedChecker;
import org.jstefek.seleniumPlayground.pages.checker.RelocationChecker;
import org.jstefek.seleniumPlayground.pages.checker.VisibilityChecker;
import org.jstefek.seleniumPlayground.pages.instanciator.PageInstanciator;
import org.jstefek.seleniumPlayground.pages.instanciator.SimplePageInstanciator;
import org.jstefek.seleniumPlayground.pages.intialize.PageInitializer;
import org.jstefek.seleniumPlayground.pages.intialize.SimplePageInitializer;
import org.openqa.selenium.WebDriver;

public class SimplePageFactory implements PageFactory {

    private final PageInitializer pageInitializer;
    private final PageInstanciator pageInstanciator;
    private final PageChecker[] pageChechers;

    public SimplePageFactory() {
        this(new SimplePageInstanciator(), new SimplePageInitializer(), new PageLoadedChecker(), new VisibilityChecker(), new RelocationChecker());
    }

    SimplePageFactory(PageInstanciator pageInstanciator, PageInitializer pageInitializer, PageChecker... pageCheckers) {
        this.pageInitializer = pageInitializer;
        this.pageInstanciator = pageInstanciator;
        this.pageChechers = pageCheckers;
    }

    @Override
    public <T extends AbstractPage> T initializePage(Class<T> pageKlass, WebDriver browser) {
        T page = pageInstanciator.instantiatePage(pageKlass, browser, this);
        pageInitializer.initializePage(page, browser);
        for (PageChecker pageChecher : pageChechers) {
            pageChecher.checkPage(page);
        }
        return page;
    }

}
