package com.mmajewski.experimental.exercise;

import java.util.*;

class Exercise {

    public static void main(String[] args) {
        Set<Class<?>> allImplementedInterfaces = findAllImplementedInterfaces(ArrayList.class);
        System.out.println(allImplementedInterfaces);
    }

    /**
     * Returns all the interfaces that the current input class implements.
     * Note: If the input is an interface itself, the method returns all the interfaces the
     * input interface extends.
     */
    static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {
        Set<Class<?>> allImplementedInterfaces = new HashSet<>();

        Class<?>[] inputInterfaces = input.getInterfaces();
        for (Class<?> currentInterface : inputInterfaces) {
            allImplementedInterfaces.add(currentInterface);

            if (currentInterface.getInterfaces().length == 0) {
                continue;
            }

            allImplementedInterfaces.addAll(findAllImplementedInterfaces(currentInterface));
        }

        return allImplementedInterfaces;
    }
}