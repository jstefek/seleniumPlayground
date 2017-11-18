package org.jstefek.seleniumPlayground.browser.enhancer;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class BrowserEnhancerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<BrowserEnhancer[]>() {
        }).toProvider(BrowserEnhancersProvider.class).in(Singleton.class);

        bind(new TypeLiteral<WebDriverEventListener[]>() {
        }).toProvider(WebDriverEventListenersProvider.class).in(Singleton.class);
    }

}
