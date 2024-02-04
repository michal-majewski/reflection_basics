package com.mmajewski.experimental.exercise;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

class ArrayFlatteningTest {

    @ParameterizedTest
    @MethodSource("examples")
    <T> void shouldConcatenateAsExpected(Class<?> elementType,  T expected, Object... givenArgs) {
        Object actual = ArrayFlattening.concat(elementType, givenArgs);

        assertArrayEquals((Object[]) expected, (Object[]) actual);
    }

    public static Stream<Arguments> examples() {
        return Stream.of(
                of(Integer.class,
                        new Integer[]{1, 2, 3, 4, 5},
                        new Integer[]{1, 2, 3, 4, 5}),
                of(Integer.class,
                        new Integer[]{1, 2, 3, 4, 5, 6, 7},
                        new Object[]{1, 2, 3, new int[]{4, 5, 6}, 7}),
                of(String.class,
                        new String[]{"a", "b", "c", "d", "e"},
                        new Object[]{new String[]{"a", "b"}, "c", new String[]{"d", "e"}})
        );
    }
}