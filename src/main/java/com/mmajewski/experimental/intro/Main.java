package com.mmajewski.experimental.intro;

import java.util.Collection;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;

        HashMap<String, Integer> mapObject = new HashMap<>();
        Class<? extends HashMap> hashMapClass = mapObject.getClass();

        Class<?> squareClass = Class.forName("com.mmajewski.experimental.intro.Main$Square");

//        printClassInfo(stringClass, hashMapClass, squareClass);

        var circleObject = new Drawable() {
            @Override
            public int getNumberOfCorners() {
                return 0;
            }
        };

        printClassInfo(Collection.class, boolean.class, int[][].class, Color.class, circleObject.getClass());
    }

    private static void printClassInfo(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            System.out.println(
                    String.format("class name: %s, class package name: %s",
                            clazz.getSimpleName(),
                            clazz.getPackageName())
            );

            Class<?>[] implementedInterfaces = clazz.getInterfaces();

            for (Class<?> implementedInterface : implementedInterfaces) {
                System.out.println(
                        String.format(
                                "class %s implements/extends: %s",
                                clazz.getSimpleName(),
                                implementedInterface.getSimpleName()
                        )
                );
            }

            System.out.println("Is array: " + clazz.isArray());
            System.out.println("Is primitive: " + clazz.isPrimitive());
            System.out.println("Is enum: " + clazz.isEnum());
            System.out.println("Is interface: " + clazz.isInterface());
            System.out.println("Is anonymous: " + clazz.isAnonymousClass());

            System.out.println();
            System.out.println();
        }
    }

    private static class Square implements Drawable {

        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }

    private interface Drawable {
        int getNumberOfCorners();
    }

    private enum Color {
        BLUE,
        RED,
        GREEN
    }
}
