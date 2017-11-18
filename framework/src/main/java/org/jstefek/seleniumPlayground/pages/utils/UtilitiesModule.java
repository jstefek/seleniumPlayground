package org.jstefek.seleniumPlayground.pages.utils;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class UtilitiesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ReflectionUtilsService.class).to(SimpleReflectionUtilsService.class).in(Singleton.class);
        bind(StringUtilsService.class).to(SimpleStringUtilsService.class).in(Singleton.class);
    }

}
