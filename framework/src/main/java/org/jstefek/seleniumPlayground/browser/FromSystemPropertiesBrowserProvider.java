package org.jstefek.seleniumPlayground.browser;

import java.util.function.Supplier;
import org.jstefek.seleniumPlayground.browser.factory.BrowserFactory;
import org.jstefek.seleniumPlayground.browser.factory.SimpleBrowserFactory;
import org.openqa.selenium.WebDriver;

public class FromSystemPropertiesBrowserProvider implements BrowserProvider {

    private static final String BROWSER_SYS_PROPERTY = "browser";

    private final BrowserFactory bf;
    private WebDriver browser;
    private final Supplier<BrowserType> browserTypeSupplier;

    public FromSystemPropertiesBrowserProvider() {
        this(new SimpleBrowserFactory(), () -> BrowserType.parseBrowser(System.getProperty(BROWSER_SYS_PROPERTY)));
    }

    FromSystemPropertiesBrowserProvider(BrowserFactory bf, Supplier<BrowserType> browserTypeSupplier) {
        this.bf = bf;
        this.browserTypeSupplier = browserTypeSupplier;
    }

    @Override
    public WebDriver getBrowser() {
        if (browser == null) {
            browser = bf.startBrowser(browserTypeSupplier.get());
        }
        return browser;
    }

}
