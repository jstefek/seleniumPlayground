package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeInstanciator extends SimpleBrowserInstanciator {

    public ChromeInstanciator() {
        super("chromedriver", "webdriver.chrome.driver", (o) -> new ChromeDriver(o), ChromeOptions::new);
    }

}
