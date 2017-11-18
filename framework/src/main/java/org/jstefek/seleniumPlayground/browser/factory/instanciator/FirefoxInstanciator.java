package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import java.util.logging.Level;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class FirefoxInstanciator extends SimpleBrowserInstanciator {

    FirefoxInstanciator() {
        super("geckodriver", "webdriver.gecko.driver", (o) -> new FirefoxDriver(o), () -> new FirefoxOptions().setLogLevel(Level.OFF));
    }
}
