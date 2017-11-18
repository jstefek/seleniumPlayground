package org.jstefek.seleniumPlayground.browser.factory;

import java.util.Map;
import javax.inject.Inject;
import org.jstefek.seleniumPlayground.browser.BrowserType;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.BrowserInstanciator;
import org.openqa.selenium.WebDriver;

class SimpleBrowserFactory implements BrowserFactory {

    private final Map<BrowserType, BrowserInstanciator> instanciators;

    @Inject
    SimpleBrowserFactory(Map<BrowserType, BrowserInstanciator> instanciators) {
        this.instanciators = instanciators;
    }

    @Override
    public WebDriver startBrowser(BrowserType bt) {
        BrowserInstanciator instanciator = instanciators.get(bt);
        if (instanciator == null) {
            throw new UnsupportedOperationException(String.format("Selected browser <%s> is not supported.", bt));
        }
        return instanciator.startBrowser();
    }

}
