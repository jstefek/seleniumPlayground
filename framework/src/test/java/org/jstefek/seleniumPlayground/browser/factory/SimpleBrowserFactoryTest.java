package org.jstefek.seleniumPlayground.browser.factory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.EnumMap;
import org.jstefek.seleniumPlayground.browser.BrowserType;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.BrowserInstanciator;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.ChromeInstanciator;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.FirefoxInstanciator;
import org.mockito.Mockito;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleBrowserFactoryTest {

    private SimpleBrowserFactory browserFactory;
    private ChromeDriver chrome;
    private ChromeInstanciator crInstaciator;
    private FirefoxInstanciator ffInstaciator;
    private FirefoxDriver firefox;

    @BeforeMethod
    public void setup() {
        crInstaciator = Mockito.mock(ChromeInstanciator.class);
        ffInstaciator = Mockito.mock(FirefoxInstanciator.class);
        firefox = Mockito.mock(FirefoxDriver.class);
        chrome = Mockito.mock(ChromeDriver.class);
        when(crInstaciator.startBrowser()).thenReturn(chrome);
        when(ffInstaciator.startBrowser()).thenReturn(firefox);
        EnumMap<BrowserType, BrowserInstanciator> map = new EnumMap<>(BrowserType.class);
        map.put(BrowserType.FIREFOX, ffInstaciator);
        map.put(BrowserType.CHROME, crInstaciator);
        browserFactory = new SimpleBrowserFactory(map);
    }

    @Test
    public void testStartBrowser_startChrome_isStarted() {
        Assert.assertTrue(browserFactory.startBrowser(BrowserType.CHROME) instanceof ChromeDriver);
        verify(crInstaciator, times(1)).startBrowser();
        verify(ffInstaciator, times(0)).startBrowser();
    }

    @Test
    public void testStartBrowser_startFirefox_isStarted() {
        Assert.assertTrue(browserFactory.startBrowser(BrowserType.FIREFOX) instanceof FirefoxDriver);
        verify(crInstaciator, times(0)).startBrowser();
        verify(ffInstaciator, times(1)).startBrowser();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testStartBrowser_startUnknown_throwsException() {
        browserFactory.startBrowser(BrowserType.UNKNOWN);
    }

}
