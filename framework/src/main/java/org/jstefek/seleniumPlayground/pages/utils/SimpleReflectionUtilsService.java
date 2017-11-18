package org.jstefek.seleniumPlayground.pages.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reflection utilities service
 */
class SimpleReflectionUtilsService implements ReflectionUtilsService {

    @Override
    public List<Field> getAllFields(Class klass) {
        List<Field> list = new ArrayList<>();
        Class actualClass = klass;
        while (!actualClass.equals(Object.class)) {
            list.addAll(Arrays.asList(actualClass.getDeclaredFields()));
            actualClass = actualClass.getSuperclass();
        }
        return list;
    }

    @Override
    public List<Field> getAllFields(Object instance) {
        return getAllFields(instance.getClass());
    }

    @Override
    public <T> List<T> getFieldsValuesWithAnnotationAndOfType(Object instance, Class<? extends Annotation> klass, Class<T> type) {
        return getFieldsWithAnnotationAndOfType(instance, klass, type)
                .stream()
                .map(f -> getTypedFieldValue(f, instance, type))
                .collect(Collectors.toList());
    }

    @Override
    public List<Field> getFieldsWithAnnotation(Object instance, Class<? extends Annotation> klass) {
        return getAllFields(instance)
                .stream()
                .filter((f) -> f.getAnnotation(klass) != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Field> getFieldsWithAnnotationAndOfType(Object instance, Class<? extends Annotation> klass, Class<?> type) {
        return getAllFields(instance)
                .stream()
                .filter(f -> f.getAnnotation(klass) != null && f.getType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public <T> T getTypedFieldValue(Field f, Object instance, Class<T> type) {
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

}
