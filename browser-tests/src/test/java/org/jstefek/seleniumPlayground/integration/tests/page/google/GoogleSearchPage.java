package org.jstefek.seleniumPlayground.integration.tests.page.google;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.PageConfiguration;
import org.jstefek.seleniumPlayground.pages.checker.annotation.PageLocation;
import org.jstefek.seleniumPlayground.pages.checker.annotation.WaitForVisibilityAfterLoad;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageLocation(urlToContain = "google.cz", titleToContain = "Google")
public class GoogleSearchPage extends AbstractPage {

    @WaitForVisibilityAfterLoad
    @FindBy(name = "q")
    private WebElement searchField;

    public GoogleSearchPage(PageConfiguration config) {
        super(config);
    }

    public GoogleSearchResultPage searchFor(String keyWord) {
        searchField.sendKeys(keyWord, Keys.ENTER);
        return createPage(GoogleSearchResultPage.class);
    }

}
