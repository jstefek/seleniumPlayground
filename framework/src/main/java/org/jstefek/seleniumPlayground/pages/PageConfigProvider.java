package org.jstefek.seleniumPlayground.pages;

import javax.inject.Inject;
import javax.inject.Provider;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.jstefek.seleniumPlayground.pages.utils.ajax.RequestGuard;
import org.openqa.selenium.WebDriver;

class PageConfigProvider implements Provider<PageConfiguration> {

    private final WebDriver browser;
    private final PageFactory pf;
    private final RequestGuard rg;

    @Inject
    PageConfigProvider(WebDriver browser, PageFactory pf, RequestGuard rg) {
        this.browser = browser;
        this.pf = pf;
        this.rg = rg;
    }

    @Override
    public PageConfiguration get() {
        return new PageConfiguration() {
            @Override
            public WebDriver getBrowser() {
                return browser;
            }

            @Override
            public PageFactory getPageFactory() {
                return pf;
            }

            @Override
            public RequestGuard getRequestGuard() {
                return rg;
            }
        };
    }

}
