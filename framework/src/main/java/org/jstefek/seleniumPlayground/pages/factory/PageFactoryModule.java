package org.jstefek.seleniumPlayground.pages.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class PageFactoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PageFactory.class).to(SimplePageFactory.class).in(Singleton.class);
    }

}
