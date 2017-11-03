package org.jstefek.seleniumPlayground.integration.tests;

import org.jstefek.seleniumPlayground.browser.BrowserProvider;
import org.jstefek.seleniumPlayground.browser.FromSystemPropertiesBrowserProvider;
import org.jstefek.seleniumPlayground.integration.tests.listener.StatusToConsolePrinter;
import org.jstefek.seleniumPlayground.integration.tests.page.google.GoogleSearchPage;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.jstefek.seleniumPlayground.pages.factory.SimplePageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(StatusToConsolePrinter.class)
public abstract class AbstractGoogleSearchTest {

    private static final String GOOGLE_CZ = "https://www.google.cz/";

    private WebDriver browser;
    private final BrowserProvider browserProvider = new FromSystemPropertiesBrowserProvider();
    private final PageFactory pageFactory = new SimplePageFactory();

    @AfterSuite
    public void destroyBrowser() {
        browser.quit();
    }

    @BeforeSuite
    public void initializeBrowser() {
        browser = browserProvider.getBrowser();
    }

    protected GoogleSearchPage openGoogleSearchPage() {
        browser.get(GOOGLE_CZ);
        return pageFactory.initializePage(GoogleSearchPage.class, browser);
    }

}
