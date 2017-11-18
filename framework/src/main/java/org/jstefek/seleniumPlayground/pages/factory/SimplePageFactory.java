package org.jstefek.seleniumPlayground.pages.factory;

import javax.inject.Inject;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.PageChecker;
import org.jstefek.seleniumPlayground.pages.instanciator.PageInstanciator;
import org.jstefek.seleniumPlayground.pages.intialize.PageInitializer;

class SimplePageFactory implements PageFactory {

    private final PageInitializer pageInitializer;
    private final PageInstanciator pageInstanciator;
    private final PageChecker[] pageChechers;

    @Inject
    SimplePageFactory(PageInstanciator pageInstanciator, PageInitializer pageInitializer, PageChecker... pageCheckers) {
        this.pageInitializer = pageInitializer;
        this.pageInstanciator = pageInstanciator;
        this.pageChechers = pageCheckers;
    }

    @Override
    public <T extends AbstractPage> T initializePage(Class<T> pageKlass) {
        T page = pageInstanciator.instantiatePage(pageKlass);
        pageInitializer.initializePage(page);
        for (PageChecker pageChecher : pageChechers) {
            pageChecher.checkPage(page);
        }
        return page;
    }

}
