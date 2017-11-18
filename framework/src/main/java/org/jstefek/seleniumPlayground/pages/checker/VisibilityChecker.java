package org.jstefek.seleniumPlayground.pages.checker;

import java.util.List;
import javax.inject.Inject;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.annotation.WaitForVisibilityAfterLoad;
import org.jstefek.seleniumPlayground.pages.utils.ReflectionUtilsService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Checks all page object's fields marked with {@link WaitForVisibilityAfterLoad WaitForVisibilityAfterLoad} to be visible.
 */
class VisibilityChecker implements PageChecker {

    private final ReflectionUtilsService reflectionUtilsService;

    @Inject
    VisibilityChecker(ReflectionUtilsService reflectionUtilsService) {
        this.reflectionUtilsService = reflectionUtilsService;
    }

    @Override
    public <T extends AbstractPage> void checkPage(T page) {
        final List<WebElement> elements = reflectionUtilsService.getFieldsValuesWithAnnotationAndOfType(page, WaitForVisibilityAfterLoad.class, WebElement.class);
        if (!elements.isEmpty()) {
            page.createWait()
                    .withMessage(() -> String.format("all elements with @%s in page object to be visible", WaitForVisibilityAfterLoad.class.getSimpleName()))
                    .until(ExpectedConditions.visibilityOfAllElements(elements));
        }
    }
}
