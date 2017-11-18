package org.jstefek.seleniumPlayground.browser;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriver;

public class BrowserProviderModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WebDriver.class).toProvider(SimpleBrowserProvider.class).in(Singleton.class);
        bind(new TypeLiteral<Supplier<BrowserType>>() {
        }).to(BrowserTypeSupplier.class).in(Singleton.class);
    }

}
