package org.jstefek.seleniumPlayground.browser.factory.instanciator;

import static org.mockito.Mockito.when;

import java.io.File;
import java.util.function.Function;
import java.util.function.Supplier;
import org.mockito.Mockito;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Window;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleBrowserInstantiatorTest {

    private static final String PROPERTY = "property";
    private static final String RESOURCE = "resource";

    private SimpleBrowserInstanciator instanciator;
    private File file;
    private final String path = "some/path";
    private Function<String, File> pathToFileFunction;
    private Function<String, String> propToPathFunction;
    private Function<? super MutableCapabilities, ? extends WebDriver> browserFunction;
    private Supplier<? extends MutableCapabilities> optionsSupplier;
    private MutableCapabilities options;

    @BeforeMethod
    public void setup() {
        browserFunction = Mockito.mock(Function.class);
        optionsSupplier = Mockito.mock(Supplier.class);
        options = Mockito.mock(MutableCapabilities.class);
        pathToFileFunction = Mockito.mock(Function.class);
        propToPathFunction = Mockito.mock(Function.class);
        file = Mockito.mock(File.class);
        instanciator = new SimpleBrowserInstanciator(browserFunction, optionsSupplier, pathToFileFunction, RESOURCE, propToPathFunction, PROPERTY);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testStartBrowser_propertyIsEmpty_throwsException() {
        when(propToPathFunction.apply(PROPERTY)).thenReturn("");

        instanciator.startBrowser();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testStartBrowser_propertyIsNull_throwsException() {
        when(propToPathFunction.apply(PROPERTY)).thenReturn(null);

        instanciator.startBrowser();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testStartBrowser_propertySetButFileNotExists_throwsException() {
        when(propToPathFunction.apply(PROPERTY)).thenReturn(path);
        when(pathToFileFunction.apply(path)).thenReturn(file);
        when(file.exists()).thenReturn(false);

        instanciator.startBrowser();
    }

    @Test
    public void testStartBrowser_propertyIsSetAndFileExists_startsBrowser() {
        when(propToPathFunction.apply(PROPERTY)).thenReturn(path);
        when(pathToFileFunction.apply(path)).thenReturn(file);
        when(file.exists()).thenReturn(true);
        when(file.setExecutable(true)).thenReturn(true);
        when(optionsSupplier.get()).thenReturn(options);
        WebDriver browser = Mockito.mock(WebDriver.class);
        when(browserFunction.apply(options)).thenReturn(browser);
        Options o = Mockito.mock(WebDriver.Options.class);
        when(browser.manage()).thenReturn(o);
        Window w = Mockito.mock(Window.class);
        when(o.window()).thenReturn(w);

        Assert.assertEquals(instanciator.startBrowser(), browser);
    }

}
