package org.jstefek.seleniumPlayground.pages.utils.ajax;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import javax.inject.Inject;
import org.jstefek.seleniumPlayground.config.BaseConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.WebDriverWait;

class SimpleRequestGuard implements RequestGuard {

    private static final String AJAX_INTERCEPTOR
            = "window.org = window.org || {};\n"
            + "window.org.jstefek = window.org.jstefek || {};\n"
            + "window.org.jstefek.guard = window.org.jstefek.guard || {\n"
            + "    intialized: false,\n"
            + "    totalRequests: 0,\n"
            + "    activeRequests: 0,\n"
            + "    intialize: function () {\n"
            + "        if (!this.initialized) {\n"
            + "            var guard = this;\n"
            + "            var originalOpenFunction = XMLHttpRequest.prototype.open;"
            + "            XMLHttpRequest.prototype.open = function (method, url, async, user, pass) {\n"
            + "                this.addEventListener('readystatechange', function () {\n"
            + "                    if (this.readyState === 1) {\n"
            + "                        guard.activeRequests++;\n"
            + "                        guard.totalRequests++;\n"
            + "                    }\n"
            + "                    if (this.readyState === 4) {\n"
            + "                        guard.activeRequests--;\n"
            + "                    }\n"
            + "                }, false);\n"
            + "                originalOpenFunction.call(this, method, url, async, user, pass);\n"
            + "            };\n"
            + "            this.intialized = true;\n"
            + "        }\n"
            + "    }\n"
            + "};"
            + "window.org.jstefek.guard.intialize();";

    private static final String NUMBER_OF_ACTIVE_AJAX_REQUESTS = "return window.org.jstefek.guard.activeRequests";
    private static final String NUMBER_OF_TOTAL_AJAX_REQUESTS = "return window.org.jstefek.guard.totalRequests";

    private final WebDriver browser;

    @Inject
    SimpleRequestGuard(WebDriver browser) {
        this.browser = browser;
    }

    @Override
    public <T extends WebElement> T guardAjax(T obj) {
        return createGuardProxy(obj);
    }

    @Override
    public void performAjaxAction(Action a) {
        createGuardProxy(a).perform();
    }

    private <T> T createGuardProxy(T obj) {
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
                new AjaxGuardHandler(browser, obj));
    }

    private static abstract class AbstractGuardHandler implements InvocationHandler {

        protected final WebDriver browser;
        protected final Object obj;

        public AbstractGuardHandler(WebDriver browser, Object obj) {
            this.browser = browser;
            this.obj = obj;
        }

        protected JavascriptExecutor getExecutor() {
            return (JavascriptExecutor) browser;
        }

    }

    private static class AjaxGuardHandler extends AbstractGuardHandler {

        public AjaxGuardHandler(WebDriver browser, Object obj) {
            super(browser, obj);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // initialize
            getExecutor().executeScript(AJAX_INTERCEPTOR);
            // save number of request before invocation
            final long numberOfRequestsBefore = (long) getExecutor().executeScript(NUMBER_OF_TOTAL_AJAX_REQUESTS);
            // invoke method
            Object result = method.invoke(obj, args);
            // wait for ajax request(s) to start and finish
            new WebDriverWait(browser, BaseConfig.INSTANCE.getBaseWaitTime().getValue(TimeUnit.SECONDS),
                    BaseConfig.INSTANCE.getPollingCycleTime().getValue(TimeUnit.MILLISECONDS))
                    .until(new AjaxRequestDoneFunction(getExecutor(), numberOfRequestsBefore));
            return result;
        }
    }

    private static class AjaxRequestDoneFunction implements Function<WebDriver, Boolean> {

        private final JavascriptExecutor executor;
        private final long numberOfRequestsBefore;
        private long totalRequestsStarted;
        private long activeRequests;

        public AjaxRequestDoneFunction(JavascriptExecutor executor, long numberOfRequestsBefore) {
            this.executor = executor;
            this.numberOfRequestsBefore = numberOfRequestsBefore;
        }

        @Override
        public Boolean apply(WebDriver t) {
            totalRequestsStarted = (long) executor.executeScript(NUMBER_OF_TOTAL_AJAX_REQUESTS);
            activeRequests = (long) executor.executeScript(NUMBER_OF_ACTIVE_AJAX_REQUESTS);
            return totalRequestsStarted > numberOfRequestsBefore && activeRequests == 0;
        }

        @Override
        public String toString() {
            if (totalRequestsStarted <= numberOfRequestsBefore) {
                return "Ajax request to start.";
            } else {
                return "Ajax request to finish.";
            }
        }
    }

}
