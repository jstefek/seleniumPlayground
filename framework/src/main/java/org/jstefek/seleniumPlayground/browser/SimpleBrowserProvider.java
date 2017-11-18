package org.jstefek.seleniumPlayground.browser;

import java.util.function.Supplier;
import javax.inject.Inject;
import javax.inject.Provider;
import org.jstefek.seleniumPlayground.browser.factory.BrowserFactory;
import org.openqa.selenium.WebDriver;

class SimpleBrowserProvider implements Provider<WebDriver> {

    private final BrowserFactory bf;
    private WebDriver browser;
    private final Supplier<BrowserType> browserTypeProvider;

    @Inject
    SimpleBrowserProvider(BrowserFactory bf, Supplier<BrowserType> browserTypeSupplier) {
        this.bf = bf;
        this.browserTypeProvider = browserTypeSupplier;
    }

    @Override
    public WebDriver get() {
        if (browser == null) {
            browser = bf.startBrowser(browserTypeProvider.get());
        }
        return browser;
    }

}
