package org.jstefek.seleniumPlayground.browser.enhancer;

import java.util.Set;
import javax.inject.Inject;
import javax.inject.Provider;
import org.jstefek.seleniumPlayground.pages.utils.ReflectionUtilsService;
import org.openqa.selenium.support.events.WebDriverEventListener;

class WebDriverEventListenersProvider implements Provider<WebDriverEventListener[]> {

    private final ReflectionUtilsService service;

    @Inject
    WebDriverEventListenersProvider(ReflectionUtilsService service) {
        this.service = service;
    }

    @Override
    public WebDriverEventListener[] get() {
        Set<Class<? extends WebDriverEventListener>> klasses = service.getAllSubTypesWithAnnotation(WebDriverEventListener.class, WDEventListener.class);
        WebDriverEventListener[] result;
        if (klasses != null && !klasses.isEmpty()) {
            result = new WebDriverEventListener[klasses.size()];
            int i = 0;
            for (Class<? extends WebDriverEventListener> k : klasses) {
                result[i++] = service.instanciate(k);
            }
        } else {
            result = new WebDriverEventListener[0];
        }
        return result;

    }
}
