package org.jstefek.seleniumPlayground.integration.tests.page.tmobile;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.PageLocation;
import org.jstefek.seleniumPlayground.pages.checker.WaitForVisibilityAfterLoad;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageLocation(urlToContain = "t-mobile.cz/podpora/obratte-se-na-nas", titleToContain = "Obraťte se na nás")
public class TMobileSupportChoicePage extends AbstractPage {

    @WaitForVisibilityAfterLoad
    @FindBy(xpath = "//a[contains(text(), 'Kontaktní formulář')]")
    private WebElement contactFormLink;

    public TMobileSupportChoicePage(WebDriver browser, PageFactory pageFactory) {
        super(browser, pageFactory);
    }

    public TMobileContactForm openContactForm() {
        contactFormLink.click();
        return createPage(TMobileContactForm.class);
    }
}
