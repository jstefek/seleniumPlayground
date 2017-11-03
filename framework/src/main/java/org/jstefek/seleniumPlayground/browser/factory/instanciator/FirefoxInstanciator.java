package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import java.util.logging.Level;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxInstanciator extends AbstractBrowserInstanciator {

    public FirefoxInstanciator() {
        super("geckodriver", "webdriver.gecko.driver", () -> new FirefoxDriver(new FirefoxOptions().setLogLevel(Level.OFF)));
    }
}
