package org.jstefek.seleniumPlayground.browser;

import java.util.function.Supplier;
import javax.inject.Inject;
import javax.inject.Provider;
import org.jstefek.seleniumPlayground.browser.enhancer.BrowserEnhancer;
import org.jstefek.seleniumPlayground.browser.factory.BrowserFactory;
import org.openqa.selenium.WebDriver;

class SimpleBrowserProvider implements Provider<WebDriver> {

    private final BrowserFactory bf;
    private WebDriver browser;
    private final Supplier<BrowserType> browserTypeProvider;
    private final BrowserEnhancer[] enhancers;

    @Inject
    SimpleBrowserProvider(BrowserFactory bf, Supplier<BrowserType> browserTypeProvider, BrowserEnhancer... enhancers) {
        this.bf = bf;
        this.browserTypeProvider = browserTypeProvider;
        this.enhancers = enhancers;
    }

    @Override
    public WebDriver get() {
        if (browser == null) {
            browser = bf.startBrowser(browserTypeProvider.get());
            for (BrowserEnhancer e : enhancers) {
                browser = e.enhance(browser);
            }
        }
        return browser;
    }

}
