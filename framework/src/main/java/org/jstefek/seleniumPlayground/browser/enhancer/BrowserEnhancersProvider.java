package org.jstefek.seleniumPlayground.browser.enhancer;

import javax.inject.Inject;
import javax.inject.Provider;

class BrowserEnhancersProvider implements Provider<BrowserEnhancer[]> {

    private final EventFiringWDEnhancer eventFiringWDEnhancer;

    @Inject
    BrowserEnhancersProvider(EventFiringWDEnhancer eventFiringWDEnhancer) {
        this.eventFiringWDEnhancer = eventFiringWDEnhancer;
    }

    @Override
    public BrowserEnhancer[] get() {
        return new BrowserEnhancer[]{eventFiringWDEnhancer};
    }

}
