package org.jstefek.seleniumPlayground.integration.tests.page.tmobile;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.PageConfiguration;
import org.jstefek.seleniumPlayground.pages.checker.annotation.PageLocation;
import org.jstefek.seleniumPlayground.pages.checker.annotation.WaitForVisibilityAfterLoad;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

@PageLocation(urlToContain = "t-mobile.cz", titleToContain = "T-Mobile.cz")
public class TMobileHomePage extends AbstractPage {

    @WaitForVisibilityAfterLoad
    @FindBy(xpath = "//a[contains(text(), 'Podpora')]")
    private WebElement supportMenuLink;

    public TMobileHomePage(PageConfiguration config) {
        super(config);
    }

    public TMobileSupportMenu openSupportMenu() {
        new Actions(getBrowser()).moveToElement(supportMenuLink).perform();
        return createPage(TMobileSupportMenu.class);
    }

}
