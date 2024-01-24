package com.mmajewski.experimental.objectcreation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        printConstructorData(Address.class);
        printConstructorData(Person.class);

        Address address = createInstanceWithArguments(Address.class, "MyStreet", 20);
        Person person = createInstanceWithArguments(Person.class, address, "John", 20);
        System.out.println(person);
    }

    static <T> T createInstanceWithArguments(Class<T> clazz, Object... args)
            throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (args.length == constructor.getParameterTypes().length) {
                return (T) constructor.newInstance(args);
            }
        }

        System.err.println("An appropriate constructor was not found");
        return null;
    }

    static void printConstructorData(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        System.out.printf("Class %s has %d declared constructors%n",
                clazz.getSimpleName(),
                constructors.length
        );

        for (int i = 0; i < constructors.length; i++) {
            Class<?>[] parameterTypes = constructors[i].getParameterTypes();

            List<String> parametersTypeNames = Arrays.stream(parameterTypes)
                    .map(Class::getSimpleName)
                    .toList();

            System.out.println(parametersTypeNames);
        }
    }
}
