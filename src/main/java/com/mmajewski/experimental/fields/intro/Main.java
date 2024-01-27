package com.mmajewski.experimental.fields.intro;

import java.lang.reflect.Field;

class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        printDeclaredFieldsInfo(Movie.class,
                new Movie("Lord of the Rings",
                        2001,
                        12.99,
                        true,
                        Category.ADVENTURE));

        Field minPriceStaticField = Movie.class.getDeclaredField("MINIMUM_PRICE");
        System.err.println(
                String.format(
                        "Static MINIMUM_PRICE from the class, value: %f",
                        minPriceStaticField.get(null)
                )
        );

//        printDeclaredFieldsInfo(Movie.MovieStats.class);
//        printDeclaredFieldsInfo(Category.class, Category.ACTION);
    }

    public static <T> void printDeclaredFieldsInfo(Class<? extends T> clazz, T instance) throws IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("Field name: %s, Type: %s\n",
                    field.getName(),
                    field.getType().getName());

            System.out.printf("Is synthentic field: %s",
                    field.isSynthetic());
            field.setAccessible(true);
            System.out.printf("Field value is: %s",
                    field.get(instance));

            System.out.println();
            System.out.println();
        }
    }

    public static void printDeclaredFieldsInfo(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("Field name: %s, Type: %s\n",
                    field.getName(),
                    field.getType().getName());

            System.out.printf("Is synthentic field: %s",
                    field.isSynthetic());

            System.out.println();
            System.out.println();
        }
    }
}
