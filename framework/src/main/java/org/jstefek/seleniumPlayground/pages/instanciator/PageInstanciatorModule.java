package org.jstefek.seleniumPlayground.pages.instanciator;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class PageInstanciatorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PageInstanciator.class).to(SimplePageInstanciator.class).in(Singleton.class);
    }

}
