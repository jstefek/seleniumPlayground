package org.jstefek.seleniumPlayground.integration.tests.page.google;

import org.jstefek.seleniumPlayground.integration.tests.page.tmobile.TMobileHomePage;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.PageConfiguration;
import org.jstefek.seleniumPlayground.pages.checker.annotation.PageLocation;
import org.jstefek.seleniumPlayground.pages.checker.annotation.WaitForVisibilityAfterLoad;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageLocation(urlToContain = "google.cz/search?", titleToContain = "t-mobile - Hledat")
public class GoogleSearchResultPage extends AbstractPage {

    @WaitForVisibilityAfterLoad
    @FindBy(xpath = "//a[contains(text(), 'T-Mobile')]")
    private WebElement tMobileLink;

    public GoogleSearchResultPage(PageConfiguration config) {
        super(config);
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
