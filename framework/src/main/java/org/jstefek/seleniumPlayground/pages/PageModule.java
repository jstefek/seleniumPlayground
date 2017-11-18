package org.jstefek.seleniumPlayground.pages;

import com.google.inject.AbstractModule;

public class PageModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PageConfiguration.class).toProvider(PageConfigProvider.class);
    }

}
