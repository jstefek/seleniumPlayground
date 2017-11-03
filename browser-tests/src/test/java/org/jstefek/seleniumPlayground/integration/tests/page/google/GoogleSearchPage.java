package org.jstefek.seleniumPlayground.integration.tests.page.google;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.WaitForVisibilityAfterLoad;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchPage extends AbstractPage {

    @WaitForVisibilityAfterLoad
    @FindBy(name = "q")
    private WebElement searchField;

    public GoogleSearchPage(WebDriver browser, PageFactory pageFactory) {
        super(browser, pageFactory);
    }

    public GoogleSearchResultPage searchFor(String keyWord) {
        searchField.sendKeys(keyWord, Keys.ENTER);
        return createPage(GoogleSearchResultPage.class);
    }

}
