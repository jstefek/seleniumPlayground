package org.jstefek.seleniumPlayground.browser.factory;

import java.util.EnumMap;
import java.util.Map;
import org.jstefek.seleniumPlayground.browser.BrowserType;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.BrowserInstanciator;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.ChromeInstanciator;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.FirefoxInstanciator;
import org.openqa.selenium.WebDriver;

public class SimpleBrowserFactory implements BrowserFactory {

    private final Map<BrowserType, BrowserInstanciator> instanciators;

    public SimpleBrowserFactory() {
        this.instanciators = new EnumMap<>(BrowserType.class);
        this.instanciators.put(BrowserType.FIREFOX, new FirefoxInstanciator());
        this.instanciators.put(BrowserType.CHROME, new ChromeInstanciator());
    }

    public SimpleBrowserFactory(Map<BrowserType, BrowserInstanciator> instanciators) {
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
