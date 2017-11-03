package org.jstefek.seleniumPlayground.pages.utils.ajax;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;

public interface RequestGuard {

    /**
     * Creates a proxy for given element. Every invocation of such guarded element should trigger an AJAX request(s). The proxy
     * will wait until a request(s) is created and all of them are finished.
     *
     * @param <T> type extending Element
     * @param element element to be guarded
     * @return guarded element
     */
    <T extends WebElement> T guardAjax(T element);

    /**
     * Performs given action guarding, that it will trigger an AJAX request. This will wait until such request is created and
     * finished.
     *
     * @param action an action, which will trigger AJAX request
     */
    void performAjaxAction(Action action);
}
