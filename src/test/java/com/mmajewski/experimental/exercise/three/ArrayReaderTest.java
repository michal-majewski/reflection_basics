package com.mmajewski.experimental.exercise.three;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArrayReaderTest {

    @Test
    void shouldReturnTheLastElement() {
        //GIVEN
        String[] array = {"A", "B", "C"};
        //WHEN
        String actualElement = ArrayReader.getArrayElement(array, -1);
        //THEN
        Assertions.assertEquals("C", actualElement);
    }

    @Test
    void shouldReturnFirstElement() {
        //GIVEN
        String[] array = {"A", "B", "C"};
        //WHEN
        String actualElement = ArrayReader.getArrayElement(array, 0);
        //THEN
        Assertions.assertEquals("A", actualElement);
    }
}