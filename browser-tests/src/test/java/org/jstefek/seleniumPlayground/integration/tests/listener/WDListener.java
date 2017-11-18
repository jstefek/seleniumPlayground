package org.jstefek.seleniumPlayground.integration.tests.listener;

import static java.lang.String.format;

import java.util.Arrays;
import org.jstefek.seleniumPlayground.browser.enhancer.WDEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

@WDEventListener
public class WDListener extends AbstractWebDriverEventListener {

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        System.out.println(format("Sending %s to %s", Arrays.toString(keysToSend), element));
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println(format("Navigated to [%s]", url));
    }

}
