package com.mmajewski.experimental.exercise;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public final class TestingFramework {
    public void runTestSuite(Class<?> testClass) throws Throwable {
        /**
         * Complete your code here
         */
        Method[] allMethods = testClass.getDeclaredMethods();

        Method beforeClass = findMethodByName(allMethods, "beforeClass");
        if (beforeClass.getParameterCount() == 0) {
            beforeClass.invoke(null, (Object[]) null);
        }

        for (Method test : findMethodsByPrefix(allMethods, "test")) {
            Constructor<?> constructor = Arrays.stream(testClass.getDeclaredConstructors()).findFirst().get();
            Object object = constructor.newInstance();

            findMethodByName(allMethods, "setupTest").invoke(object, (Object[]) null);
            test.invoke(object, (Object[]) null);
        }

        findMethodByName(allMethods, "afterClass").invoke(null, (Object[]) null);
    }

    /**
     * Helper method to find a method by name
     * Returns null if a method with the given name does not exist
     */
    private Method findMethodByName(Method[] methods, String name) {
        /**
         * Complete your code here
         */
        return Arrays.stream(methods)
                .filter(method -> method.getName().equals(name))
                .findFirst().orElseGet(() -> null);
    }

    /**
     * Helper method to find all the methods that start with the given prefix
     */
    private List<Method> findMethodsByPrefix(Method[] methods, String prefix) {
        /**
         * Complete your code here
         */
        return Arrays.stream(methods)
                .filter(method -> method.getName().startsWith(prefix))
                .toList();
    }
}
