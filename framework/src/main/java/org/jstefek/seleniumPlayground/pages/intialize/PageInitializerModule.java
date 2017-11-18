package org.jstefek.seleniumPlayground.pages.intialize;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class PageInitializerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PageInitializer.class).to(SimplePageInitializer.class).in(Singleton.class);
    }

}
