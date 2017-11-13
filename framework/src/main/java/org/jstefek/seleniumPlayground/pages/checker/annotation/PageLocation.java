package org.jstefek.seleniumPlayground.pages.checker.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation for a Page object class to be checked after the page is loaded. Used by
 * {@link org.jstefek.seleniumPlayground.pages.checker.RelocationChecker RelocationChecker}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PageLocation {

    String urlToBe() default "";

    String urlToMatch() default "";

    String urlToContain() default "";

    String titleToContain() default "";

    String titleToMatch() default "";

    String titleToBe() default "";
}
