package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeInstanciator extends AbstractBrowserInstanciator {

    public ChromeInstanciator() {
        super("chromedriver", "webdriver.chrome.driver", ChromeDriver::new);
    }

}
