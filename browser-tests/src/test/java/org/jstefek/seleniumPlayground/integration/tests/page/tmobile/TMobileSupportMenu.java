package org.jstefek.seleniumPlayground.integration.tests.page.tmobile;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.PageConfiguration;
import org.jstefek.seleniumPlayground.pages.checker.annotation.WaitForVisibilityAfterLoad;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TMobileSupportMenu extends AbstractPage {

    @WaitForVisibilityAfterLoad
    @FindBy(xpath = "//a[contains(text(), 'Obraťte se na nás')]")
    private WebElement supportFormLink;

    public TMobileSupportMenu(PageConfiguration config) {
        super(config);
    }

    public TMobileSupportChoicePage openSupportChoicePage() {
        supportFormLink.click();
        return createPage(TMobileSupportChoicePage.class);
    }

}
