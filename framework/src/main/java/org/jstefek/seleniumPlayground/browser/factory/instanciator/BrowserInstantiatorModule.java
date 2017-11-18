package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import java.util.Map;
import org.jstefek.seleniumPlayground.browser.BrowserType;

public class BrowserInstantiatorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<Map<BrowserType, BrowserInstanciator>>() {
        }).toProvider(BrowserInstantiatorsProvider.class).in(Singleton.class);
    }

}
