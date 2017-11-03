package org.jstefek.seleniumPlayground.pages.checker;

import java.util.List;
import java.util.stream.Collectors;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.utils.ReflectionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Extended behaviour of {@link SimplePageLoadedChecker SimplePageLoadedChecker}, after the default check provided in
 * {@link SimplePageLoadedChecker SimplePageLoadedChecker} it checks all page object's fields marked with
 * {@link WaitForVisibilityAfterLoad WaitForVisibilityAfterLoad} to be visible.
 */
public class VisibilityCheckingPageLoadedChecker extends SimplePageLoadedChecker {

    @Override
    public <T extends AbstractPage> void waitForPageToLoad(T page) {
        super.waitForPageToLoad(page);
        final List<WebElement> elements = ReflectionUtils.getFieldsWithAnnotationAndOfType(page, WaitForVisibilityAfterLoad.class, WebElement.class)
                .stream()
                .map(f -> ReflectionUtils.getTypedFieldValue(f, page, WebElement.class))
                .collect(Collectors.toList());
        page.createWait()
                .withMessage(() -> String.format("all elements with @%s in page object to be visible", WaitForVisibilityAfterLoad.class.getSimpleName()))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}
