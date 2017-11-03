package org.jstefek.seleniumPlayground.integration.tests.page.google;

import org.jstefek.seleniumPlayground.integration.tests.page.tmobile.TMobileHomePage;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.WaitForVisibilityAfterLoad;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchResultPage extends AbstractPage {

    @WaitForVisibilityAfterLoad
    @FindBy(xpath = "//a[contains(text(), 'T-Mobile')]")
    private WebElement tMobileLink;

    public GoogleSearchResultPage(WebDriver browser, PageFactory pageFactory) {
        super(browser, pageFactory);
    }

    public TMobileHomePage browseToFirstTMobileSite() {
        tMobileLink.click();
        return createPage(TMobileHomePage.class);
    }

    @Override
    public void waitForLoad() {
        super.waitForLoad();
        // other way to wait for page is loaded
//        createWait().until(ExpectedConditions.visibilityOf(tMobileLink));
    }

}
