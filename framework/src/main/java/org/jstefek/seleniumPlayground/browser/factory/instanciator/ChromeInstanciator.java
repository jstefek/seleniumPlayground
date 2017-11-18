package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class ChromeInstanciator extends SimpleBrowserInstanciator {

    ChromeInstanciator() {
        super("chromedriver", "webdriver.chrome.driver", (o) -> new ChromeDriver(o), ChromeOptions::new);
    }

}
