package com.mmajewski.experimental.exercise;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Array.*;

final class ArrayFlattening {
    private ArrayFlattening() {
    }

    /**
     * In this exercise, we are going to implement a method that performs "smart concatenation" of elements.
     * Input:
     * The type T represents the type of elements the method should return.
     * A variable number of arguments.
     * The arguments can be of:
     * Some type T
     * An array of type T
     * A combination of arrays of type T and elements of type T.
     * Output: A flattened array containing all the input elements of type T.
     * <p/>
     * Example 1:
     * Integer [] result = concat(Integer.class, 1,2,3,4,5);
     * The result will be an array of 5 integers containing the following elements: [1,2,3,4,5]
     * <p/>
     * Example 2:
     * int [] result = contact(int.class, 1, 2, 3, new int[] {4, 5, 6}, 7);
     * The result will be an array of 7 integers containing the elements: [1, 2, 3, 4, ,5, 6, 7];
     * <p/>
     * Example 3:
     * String [] result = contact(String.class, new String[]{"a", "b"}, "c", new String[] {"d", "e"});
     * The result will be an array of 5 Strings containing the elements : ["a", "b", "c", "d", "e"]
     */
    public static <T> T concat(Class<?> type, Object... arguments) {
        if (arguments.length == 0) {
            return null;
        }

        /**
         * Complete code here
         */
        List<Object> tempElementsCollection = new ArrayList<>();

        for (Object argument : arguments) {
            if (argument.getClass().isArray()) {
                int length = getLength(argument);

                for (int i = 0 ; i < length ; i++) {
                    tempElementsCollection.add(get(argument, i));
                }
            } else {
                tempElementsCollection.add(argument);
            }
        }

        Object array = newInstance(type, tempElementsCollection.size());

        for (int i = 0; i <tempElementsCollection.size() ; i++) {
            set(array, i, tempElementsCollection.get(i));
        }

        return (T) array;
    }

}
