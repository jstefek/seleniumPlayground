package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import static org.mockito.Mockito.when;

import java.io.File;
import java.util.function.Function;
import java.util.function.Supplier;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Window;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AbstractBrowserInstantiatorTest {

    private static final String PROPERTY = "property";
    private static final String RESOURCE = "resource";

    private AbstractBrowserInstanciator bi;
    private File file;
    private final String path = "some/path";
    private Function<String, File> pathToFileFunction;
    private Function<String, String> propToPathFunction;
    private Supplier<? extends WebDriver> supplier;

    @BeforeMethod
    public void setup() {
        supplier = Mockito.mock(Supplier.class);
        pathToFileFunction = Mockito.mock(Function.class);
        propToPathFunction = Mockito.mock(Function.class);
        file = Mockito.mock(File.class);
        bi = new AbstractBrowserInstantiatorImpl(supplier, pathToFileFunction, RESOURCE, propToPathFunction, PROPERTY);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testStartBrowser_propertyIsEmpty_throwsException() {
        when(propToPathFunction.apply(PROPERTY)).thenReturn("");

        bi.startBrowser();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testStartBrowser_propertyIsNull_throwsException() {
        when(propToPathFunction.apply(PROPERTY)).thenReturn(null);

        bi.startBrowser();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testStartBrowser_propertySetButFileNotExists_throwsException() {
        when(propToPathFunction.apply(PROPERTY)).thenReturn(path);
        when(pathToFileFunction.apply(path)).thenReturn(file);
        when(file.exists()).thenReturn(false);

        bi.startBrowser();
    }

    @Test
    public void testStartBrowser_propertySetFileExists_startsBrowser() {
        when(propToPathFunction.apply(PROPERTY)).thenReturn(path);
        when(pathToFileFunction.apply(path)).thenReturn(file);
        when(file.exists()).thenReturn(true);
        when(file.setExecutable(true)).thenReturn(true);
        WebDriver browser = Mockito.mock(WebDriver.class);
        when(supplier.get()).thenReturn(browser);
        Options o = Mockito.mock(WebDriver.Options.class);
        when(browser.manage()).thenReturn(o);
        Window w = Mockito.mock(Window.class);
        when(o.window()).thenReturn(w);

        Assert.assertEquals(bi.startBrowser(), browser);
    }

    public class AbstractBrowserInstantiatorImpl extends AbstractBrowserInstanciator {

        public AbstractBrowserInstantiatorImpl(Supplier<? extends WebDriver> browserSupplier, Function<String, File> pathRoFileFunction, String resourceName, Function<String, String> sysPropToPathFunction, String sysPropertyName) {
            super(browserSupplier, pathRoFileFunction, resourceName, sysPropToPathFunction, sysPropertyName);
        }
    }

}
