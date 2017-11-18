package org.jstefek.seleniumPlayground.pages.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public interface ReflectionUtilsService {

    /**
     * @param klass class from which the fields be scanned
     * @return all fields from given class up to the Object class (exclusive)
     */
    List<Field> getAllFields(Class klass);

    /**
     * @param instance instance from which the fields be scanned
     * @return all fields from given instance up to the Object class (exclusive)
     */
    List<Field> getAllFields(Object instance);

    /**
     * @param <T> searched type
     * @param ofType parent type
     * @param annotationClass annotation type
     * @return set of all classes with given annotation or an empty set
     */
    <T> Set<Class<? extends T>> getAllSubTypesWithAnnotation(Class<T> ofType, Class<? extends Annotation> annotationClass);

    /**
     * @param <T> type of fields
     * @param instance instance from which the fields be scanned
     * @param klass type of the searched annotation
     * @param type type of the field to be searched for
     * @return all fields values from given instance up to the Object class (exclusive) which are marked with given annotation
     * and are of given type
     */
    <T> List<T> getFieldsValuesWithAnnotationAndOfType(Object instance, Class<? extends Annotation> klass, Class<T> type);

    /**
     * @param instance instance from which the fields be scanned
     * @param klass type of the searched annotation
     * @return all fields from given instance up to the Object class (exclusive) which are marked with given annotation
     */
    List<Field> getFieldsWithAnnotation(Object instance, Class<? extends Annotation> klass);

    /**
     * @param instance instance from which the fields be scanned
     * @param klass type of the searched annotation
     * @param type type of the field to be searched for
     * @return all fields from given instance up to the Object class (exclusive) which are marked with given annotation and are
     * of given type
     */
    List<Field> getFieldsWithAnnotationAndOfType(Object instance, Class<? extends Annotation> klass, Class<?> type);

    /**
     * @param <T> return type
     * @param f field from which the value will be taken from
     * @param instance instance from which the field value will be taken from
     * @param type class of the return type
     * @return value from given field from given instance of given type
     */
    <T> T getTypedFieldValue(Field f, Object instance, Class<T> type);

    /**
     * @param <T> type of instance
     * @param klass class to instanciate
     * @param args arguments for constructor
     * @return new instance of given class or a RuntimeException
     */
    <T> T instanciate(Class<T> klass, Object... args);
}
