package org.jstefek.seleniumPlayground.pages.checker.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation for a WebElement field, which should be checked for visibility after its page object is instantiated. Used
 * by {@link VisibilityCheckingPageLoadedChecker VisibilityCheckingPageLoadedChecker}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface WaitForVisibilityAfterLoad {
}
