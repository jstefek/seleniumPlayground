package org.jstefek.seleniumPlayground.browser.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class BrowserFactoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BrowserFactory.class).to(SimpleBrowserFactory.class).in(Singleton.class);
    }

}
