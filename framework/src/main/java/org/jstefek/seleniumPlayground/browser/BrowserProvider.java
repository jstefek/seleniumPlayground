package org.jstefek.seleniumPlayground.browser;

import org.openqa.selenium.WebDriver;

public interface BrowserProvider {

    /**
     * @return returns WebDriver instance, creates a new one when there is no instance available.
     */
    WebDriver getBrowser();

}
