package org.jstefek.seleniumPlayground.pages;

import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.jstefek.seleniumPlayground.pages.utils.ajax.RequestGuard;
import org.openqa.selenium.WebDriver;

public interface PageConfiguration {

    WebDriver getBrowser();

    PageFactory getPageFactory();

    RequestGuard getRequestGuard();

}
