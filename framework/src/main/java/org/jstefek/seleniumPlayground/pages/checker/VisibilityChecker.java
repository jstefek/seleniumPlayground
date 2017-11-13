package org.jstefek.seleniumPlayground.pages.checker;

import org.jstefek.seleniumPlayground.pages.checker.annotation.WaitForVisibilityAfterLoad;
import java.util.List;
import java.util.stream.Collectors;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.utils.ReflectionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Checks all page object's fields marked with {@link WaitForVisibilityAfterLoad WaitForVisibilityAfterLoad} to be visible.
 */
public class VisibilityChecker implements PageChecker {

    @Override
    public <T extends AbstractPage> void checkPage(T page) {
        final List<WebElement> elements = ReflectionUtils.getFieldsWithAnnotationAndOfType(page, WaitForVisibilityAfterLoad.class, WebElement.class)
                .stream()
                .map(f -> ReflectionUtils.getTypedFieldValue(f, page, WebElement.class))
                .collect(Collectors.toList());
        page.createWait()
                .withMessage(() -> String.format("all elements with @%s in page object to be visible", WaitForVisibilityAfterLoad.class.getSimpleName()))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}
