package org.jstefek.seleniumPlayground.browser.factory;

import org.jstefek.seleniumPlayground.browser.BrowserType;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.ChromeInstanciator;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.FirefoxInstanciator;
import org.openqa.selenium.WebDriver;

public class SimpleBrowserFactory implements BrowserFactory {

    private final ChromeInstanciator chromeInstantiator;
    private final FirefoxInstanciator firefoxInstantiator;

    public SimpleBrowserFactory() {
        this(new ChromeInstanciator(), new FirefoxInstanciator());
    }

    public SimpleBrowserFactory(ChromeInstanciator chromeInstantiator, FirefoxInstanciator firefoxInstantiator) {
        this.chromeInstantiator = chromeInstantiator;
        this.firefoxInstantiator = firefoxInstantiator;
    }

    @Override
    public WebDriver startBrowser(BrowserType bt) {
        WebDriver browser = null;
        switch (bt) {
            case CHROME:
                browser = chromeInstantiator.startBrowser();
                break;
            case FIREFOX:
                browser = firefoxInstantiator.startBrowser();
                break;
            case UNKNOWN:
            default:
                throw new UnsupportedOperationException(String.format("Selected browser <%s> is not supported.", bt));
        }
        return browser;
    }

}
