package org.jstefek.seleniumPlayground.integration.tests.page.tmobile;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.annotation.WaitForVisibilityAfterLoad;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TMobileSupportMenu extends AbstractPage {

    @WaitForVisibilityAfterLoad
    @FindBy(xpath = "//a[contains(text(), 'Obraťte se na nás')]")
    private WebElement supportFormLink;

    public TMobileSupportMenu(WebDriver browser, PageFactory pageFactory) {
        super(browser, pageFactory);
    }

    public TMobileSupportChoicePage openSupportChoicePage() {
        supportFormLink.click();
        return createPage(TMobileSupportChoicePage.class);
    }

}
