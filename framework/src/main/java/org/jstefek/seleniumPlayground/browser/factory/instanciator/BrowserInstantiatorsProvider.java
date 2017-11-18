package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import java.util.EnumMap;
import java.util.Map;
import javax.inject.Provider;
import org.jstefek.seleniumPlayground.browser.BrowserType;

class BrowserInstantiatorsProvider implements Provider<Map<BrowserType, BrowserInstanciator>> {

    @Override
    public Map<BrowserType, BrowserInstanciator> get() {
        Map<BrowserType, BrowserInstanciator> instanciators = new EnumMap<>(BrowserType.class);
        instanciators.put(BrowserType.FIREFOX, new FirefoxInstanciator());
        instanciators.put(BrowserType.CHROME, new ChromeInstanciator());
        return instanciators;
    }

}
