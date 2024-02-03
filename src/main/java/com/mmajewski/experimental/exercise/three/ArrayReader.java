package com.mmajewski.experimental.exercise.three;

import java.util.function.IntPredicate;

import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;

final class ArrayReader {
    private ArrayReader() {
    }

    static final IntPredicate IS_NEGATIVE = i -> i < 0;

    /**
     * @param array object of type array
     * @param index An index of the element we want to read. The index can be both positive, negative, and zero.
     *
     * @apiNote
     * If the index is non-negative, the method returns the element at the given index,</br> counting from the beginning of the array.
     * If the index is negative, the method will return the element at the given index from the end of the array.
     * <p></p>
     * For example, for the inputs:
     * <pre>
     * int [] input = new int[] {0, 10, 20, 30, 40};
     * and the index = 3.
     * The output is 30.
     *
     * For the inputs:
     * String[] names = new String[] {"John", "Michael", "Joe", "David"};
     * and index = -1;
     * The output is "David".
     * </pre>
     */
    public static <T> T getArrayElement(Object[] array, int index) {
        if (IS_NEGATIVE.test(index)) {
            return (T) get(array, getLength(array) + index);
        }

        return (T) get(array, index);
    }
}
