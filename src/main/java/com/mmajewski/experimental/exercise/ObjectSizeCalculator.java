package com.mmajewski.experimental.exercise;

import java.lang.reflect.*;

final class ObjectSizeCalculator {
    private ObjectSizeCalculator() {
    }

    private static final long HEADER_SIZE = 12;
    private static final long REFERENCE_SIZE = 4;

    /**
     * header size = 12 bytes
     *
     * Object reference = 4 bytes (that is correct for JVM with a heap size smaller than 32 GB, which is good enough for our estimation)
     *
     * To keep things simple, the only types of fields our class can have are:
     * int
     * byte
     * long
     * double
     * float
     * short
     * String
     * (Others can be easily added later)
     * Also, we can assume that the class does not inherit any fields from superclasses
     *
     * For example, given this class:
     *
     * public class AccountSummary {
     *     private final String firstName;
     *     private final String lastName;
     *     private final short age;
     *     private final int salary;
     *
     *     public AccountSummary(String firstName, String lastName, short age, int salary) {
     *         this.firstName = firstName;
     *         this.lastName = lastName;
     *         this.age = age;
     *         this.salary = salary
     *     }
     * }
     * And this object as input:
     *
     * AccountSummary accountSummary = new AccountSummary("John", "Smith", 20, 100_000);
     * The estimate for the size of the object will be
     *
     * SizeOf(accountSummary) = {header} + {reference} + SizeOf{firstName} +SizeOf{lastName} + SizeOf{age} + SizeOf{salary} =
     *
     * 12 + 4 + (12 + 4 + 4) + (12 + 4 + 5) + 2 + 4 = 63
     *
     * A method to calculate each supported type's size is provided for your convenience.
     */
    public static long sizeOfObject(Object input) throws IllegalAccessException {
        //An initial value
        long size = HEADER_SIZE + REFERENCE_SIZE;

        for (Field field : input.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Class<?> type = field.getType();

            size += type.isPrimitive() ?
                    sizeOfPrimitiveType(type) :
                    sizeOfString(field.get(input).toString());
        }

        return size;
    }


    /*************** Helper methods ********************************/
    private static long sizeOfPrimitiveType(Class<?> primitiveType) {
        if (primitiveType.equals(int.class)) {
            return 4;
        } else if (primitiveType.equals(long.class)) {
            return 8;
        } else if (primitiveType.equals(float.class)) {
            return 4;
        } else if (primitiveType.equals(double.class)) {
            return 8;
        } else if (primitiveType.equals(byte.class)) {
            return 1;
        } else if (primitiveType.equals(short.class)) {
            return 2;
        }
        throw new IllegalArgumentException(String.format("Type: %s is not supported", primitiveType));
    }

    private static long sizeOfString(String inputString) {
        int stringBytesSize = inputString.getBytes().length;
        return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
    }
}