package org.jstefek.seleniumPlayground.browser.factory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.EnumMap;
import org.jstefek.seleniumPlayground.browser.BrowserType;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.BrowserInstanciator;
import org.mockito.Mockito;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleBrowserFactoryTest {

    private SimpleBrowserFactory browserFactory;
    private BrowserInstanciator ffInstanciator;
    private BrowserInstanciator crInstanciator;
    private ChromeDriver chrome;
    private FirefoxDriver firefox;

    @BeforeMethod
    public void setup() {
        chrome = Mockito.mock(ChromeDriver.class);
        firefox = Mockito.mock(FirefoxDriver.class);

        crInstanciator = Mockito.mock(BrowserInstanciator.class);
        ffInstanciator = Mockito.mock(BrowserInstanciator.class);
        when(crInstanciator.startBrowser()).thenReturn(chrome);
        when(ffInstanciator.startBrowser()).thenReturn(firefox);

        EnumMap<BrowserType, BrowserInstanciator> map = new EnumMap<>(BrowserType.class);
        map.put(BrowserType.FIREFOX, ffInstanciator);
        map.put(BrowserType.CHROME, crInstanciator);
        browserFactory = new SimpleBrowserFactory(map);
    }

    @Test
    public void testStartBrowser_startChrome_isStarted() {
        Assert.assertTrue(browserFactory.startBrowser(BrowserType.CHROME) instanceof ChromeDriver);
        verify(crInstanciator, times(1)).startBrowser();
        verify(ffInstanciator, times(0)).startBrowser();
    }

    @Test
    public void testStartBrowser_startFirefox_isStarted() {
        Assert.assertTrue(browserFactory.startBrowser(BrowserType.FIREFOX) instanceof FirefoxDriver);
        verify(crInstanciator, times(0)).startBrowser();
        verify(ffInstanciator, times(1)).startBrowser();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testStartBrowser_startUnknown_throwsException() {
        browserFactory.startBrowser(BrowserType.UNKNOWN);
    }

}
