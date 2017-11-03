package org.jstefek.seleniumPlayground.pages.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reflection utilities
 */
public final class ReflectionUtils {

    /**
     * @param klass class from which the fields be scanned
     * @return all fields from given class up to the Object class (exclusive)
     */
    public static List<Field> getAllFields(Class klass) {
        List<Field> list = new ArrayList<>();
        Class actualClass = klass;
        while (!actualClass.equals(Object.class)) {
            list.addAll(Arrays.asList(actualClass.getDeclaredFields()));
            actualClass = actualClass.getSuperclass();
        }
        return list;
    }

    /**
     * @param instance instance from which the fields be scanned
     * @return all fields from given instance up to the Object class (exclusive)
     */
    public static List<Field> getAllFields(Object instance) {
        return getAllFields(instance.getClass());
    }

    /**
     * @param instance instance from which the fields be scanned
     * @param klass type of the searched annotation
     * @return all fields from given instance up to the Object class (exclusive) which are marked with given annotation
     */
    public static List<Field> getFieldsWithAnnotation(Object instance, Class<? extends Annotation> klass) {
        return getAllFields(instance)
                .stream()
                .filter((f) -> f.getAnnotation(klass) != null)
                .collect(Collectors.toList());
    }

    /**
     * @param instance instance from which the fields be scanned
     * @param klass type of the searched annotation
     * @param type type of the field to be searched for
     * @return all fields from given instance up to the Object class (exclusive) which are marked with given annotation and are
     * of given type
     */
    public static List<Field> getFieldsWithAnnotationAndOfType(Object instance, Class<? extends Annotation> klass, Class<?> type) {
        return getAllFields(instance)
                .stream()
                .filter(f -> f.getAnnotation(klass) != null)
                .filter(f -> f.getType().equals(type))
                .collect(Collectors.toList());
    }

    /**
     * @param <T> return type
     * @param f field from which the value will be taken from
     * @param instance instance from which the field value will be taken from
     * @param type class of the return type
     * @return value from given field from given instance of given type
     */
    public static <T> T getTypedFieldValue(Field f, Object instance, Class<T> type) {
        try {
            boolean accessible = f.isAccessible();
            if (!accessible) {
                f.setAccessible(true);
            }
            T value = (T) f.get(instance);
            f.setAccessible(accessible);
            return value;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private ReflectionUtils() {
        throw new IllegalStateException("Cannot create utility class");
    }
}
