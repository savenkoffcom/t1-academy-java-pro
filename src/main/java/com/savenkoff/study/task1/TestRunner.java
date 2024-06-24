package com.savenkoff.study.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class TestRunner {
    public static void runTests(Class aClass) {
        System.out.println("runTests Started!");
        Method[] methods = aClass.getDeclaredMethods();
        System.out.println("Methods found: " + methods.length);
        List<Class> anyUsedAnnotations = List.of(BeforeSuite.class,AfterSuite.class, Test.class);
        List<Class> onlyUsedAnnotations = List.of(BeforeSuite.class,AfterSuite.class);
        List<Class> staticMethodsUsedAnnotations = List.of(BeforeSuite.class,AfterSuite.class);
        System.out.println("Start Checking process...");
        Map<String,Method> listAnnoChecker = new TreeMap<>();
        for (Method method : methods) {
            boolean methodIsStatic = Modifier.isStatic(method.getModifiers());
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                String annotationClassName = annotation.annotationType().getName();
                if (staticMethodsUsedAnnotations.contains(annotation.annotationType()) && !methodIsStatic)
                    throw new IllegalCallerException("Annotation " + annotationClassName + " most be used only in static method");
                if (annotation.annotationType().equals(Test.class)) {
                    Test testAnnotation = method.getAnnotation(Test.class);
                    if (testAnnotation.priority() < 1 || testAnnotation.priority() > 10)
                        throw new IllegalArgumentException("Annotation " + annotationClassName + " priority parameter most in range min 1 max 10");
                }
                if (onlyUsedAnnotations.contains(annotation.annotationType()) && listAnnoChecker.containsKey(annotationClassName))
                    throw new IllegalArgumentException(
                            "Annotation " + annotationClassName + " before exists in method " + aClass + "." + listAnnoChecker.get(annotationClassName).getName()
                    );
                listAnnoChecker.put(annotationClassName, method);
            }
        }
        System.out.println("End Checking process.");
        System.out.println("Start sorting...");
        List<Method> methodList = Arrays.stream(methods)
                .filter(method ->
                // Убрали методы без наших аннотаций
                    Arrays.stream(method.getAnnotations()).filter(annotation -> anyUsedAnnotations.contains(annotation.annotationType())).count() > 0
                )
                .sorted(TestRunner::methodsComparator)
                .toList();
        System.out.println("End sorting.");
        System.out.println("Start testing...");
        try {
            Object aClassObject = aClass.newInstance();
            for (Method method : methodList) {
                method.setAccessible(true);
                method.invoke(aClassObject);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End testing.");
    }

    // Comparator сравнения методов для сортировки порядка выполнения
    private static int methodsComparator(Method method1, Method method2) {
        return getMethodPriority(method1) - getMethodPriority(method2);
    }

    // Получение приоритета выполнения метода по его аннотации соблюдая контракт компаратора
    private static int getMethodPriority(Method method) {
        if (method.isAnnotationPresent(BeforeSuite.class))
            return -1;
        if (method.isAnnotationPresent(AfterSuite.class))
            return 11;
        if (method.isAnnotationPresent(Test.class)) {
            Test testAnnotation = method.getAnnotation(Test.class);
            return testAnnotation.priority();
        }
        return 0;
    }
}
