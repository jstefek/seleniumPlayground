package org.jstefek.seleniumPlayground.browser.enhancer;

import javax.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

class EventFiringWDEnhancer implements BrowserEnhancer {

    private final WebDriverEventListener[] listeners;

    @Inject
    EventFiringWDEnhancer(WebDriverEventListener[] listeners) {
        this.listeners = listeners;
    }

    @Override
    public <T extends WebDriver> T enhance(WebDriver browser) {
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(browser);
        for (WebDriverEventListener l : listeners) {
            eventFiringWebDriver.register(l);
        }
        return (T) eventFiringWebDriver;
    }

}
