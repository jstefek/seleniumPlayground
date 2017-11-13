package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import java.io.File;
import java.util.function.Function;
import java.util.function.Supplier;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

abstract class AbstractBrowserInstanciator implements BrowserInstanciator {

    private final Supplier<? extends WebDriver> browserSupplier;
    private final Function<String, File> pathToFileFunction;
    private final String resourceName;
    private final Function<String, String> sysPropToPathFunction;
    private final String sysPropertyName;

    AbstractBrowserInstanciator(Supplier<? extends WebDriver> browserSupplier, Function<String, File> pathToFileFunction, String resourceName, Function<String, String> sysPropToPathFunction, String sysPropertyName) {
        this.browserSupplier = browserSupplier;
        this.pathToFileFunction = pathToFileFunction;
        this.resourceName = resourceName;
        this.sysPropToPathFunction = sysPropToPathFunction;
        this.sysPropertyName = sysPropertyName;
    }

    public AbstractBrowserInstanciator(String resourceName, String sysPropertyName, Supplier<? extends WebDriver> browserSupplier) {
        this(browserSupplier, (path) -> new File(path), resourceName, (p) -> System.getProperty(p), sysPropertyName);
    }

    @Override
    public WebDriver startBrowser() {
        WebDriver browser;
        String pathToDriver = sysPropToPathFunction.apply(sysPropertyName);
        if (pathToDriver == null || pathToDriver.isEmpty()) {
            throw new IllegalStateException(String.format("Required system property <%s> was not set.", sysPropertyName));
        }
        File driverFile = pathToFileFunction.apply(pathToDriver);
        if (driverFile.exists()) {
            driverFile.setExecutable(true);
            browser = browserSupplier.get();
            browser.manage().window().setSize(new Dimension(1280, 960));
        } else {
            throw new IllegalStateException(String.format("<%s> was not found at <%s>, from property <%s>.", resourceName, pathToDriver, sysPropertyName));
        }
        return browser;
    }

}
