package org.jstefek.seleniumPlayground.pages.utils.ajax;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class AjaxUtilitiesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RequestGuard.class).to(SimpleRequestGuard.class).in(Singleton.class);
    }

}
