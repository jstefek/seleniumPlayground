package org.jstefek.seleniumPlayground.browser.enhancer;

import org.openqa.selenium.WebDriver;

public interface BrowserEnhancer {

    <T extends WebDriver> T enhance(WebDriver browser);
}
