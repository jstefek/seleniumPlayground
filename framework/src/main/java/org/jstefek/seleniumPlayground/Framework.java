package org.jstefek.seleniumPlayground;

import com.google.inject.Module;
import com.google.inject.util.Modules;
import org.jstefek.seleniumPlayground.browser.BrowserProviderModule;
import org.jstefek.seleniumPlayground.browser.enhancer.BrowserEnhancerModule;
import org.jstefek.seleniumPlayground.browser.factory.BrowserFactoryModule;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.BrowserInstantiatorModule;
import org.jstefek.seleniumPlayground.pages.PageModule;
import org.jstefek.seleniumPlayground.pages.checker.PageCheckersModule;
import org.jstefek.seleniumPlayground.pages.factory.PageFactoryModule;
import org.jstefek.seleniumPlayground.pages.instanciator.PageInstanciatorModule;
import org.jstefek.seleniumPlayground.pages.intialize.PageInitializerModule;
import org.jstefek.seleniumPlayground.pages.utils.UtilitiesModule;
import org.jstefek.seleniumPlayground.pages.utils.ajax.AjaxUtilitiesModule;

public class Framework {

    public static Module getModule() {
        return Modules.combine(
                new BrowserProviderModule(),
                new BrowserEnhancerModule(),
                new BrowserFactoryModule(),
                new BrowserInstantiatorModule(),
                new PageModule(),
                new PageCheckersModule(),
                new PageFactoryModule(),
                new PageInstanciatorModule(),
                new PageInitializerModule(),
                new UtilitiesModule(),
                new AjaxUtilitiesModule());
    }
}
