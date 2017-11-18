package org.jstefek.seleniumPlayground.integration.tests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jstefek.seleniumPlayground.Framework;
import org.jstefek.seleniumPlayground.integration.tests.listener.StatusToConsolePrinter;
import org.jstefek.seleniumPlayground.integration.tests.page.google.GoogleSearchPage;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(StatusToConsolePrinter.class)
public abstract class AbstractGoogleSearchTest {

    private static final String GOOGLE_CZ = "https://www.google.cz/";

    private WebDriver browser;
    private PageFactory pageFactory;

    @AfterSuite
    public void destroyBrowser() {
        if (browser != null) {
            browser.quit();
        }
    }

    @BeforeSuite
    public void initialize() {
        Injector injector = Guice.createInjector(Framework.getModule());
        browser = injector.getInstance(WebDriver.class);
        pageFactory = injector.getInstance(PageFactory.class);
    }

    protected GoogleSearchPage openGoogleSearchPage() {
        browser.get(GOOGLE_CZ);
        return pageFactory.initializePage(GoogleSearchPage.class);
    }

}
