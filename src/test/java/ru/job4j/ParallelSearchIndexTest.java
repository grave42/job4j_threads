package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.job4j.ParallelSearchIndex.findIndex;

class ParallelSearchIndexTest {

    @Test
    void thenIntegerArraySuccess() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        int value = 12;
        int expectedIndex = 11;
        int actualIndex = findIndex(array, value);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void thenStringArraySuccess() {
        String[] array = {"da", "lol", "ok", "neok", "ok", "ok", "ok", "ok", "ok", "ok", "ok", "net", "ok"};
        String value = "net";
        int expectedIndex = 11;
        int actualIndex = findIndex(array, value);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void thenFailed() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        int value = 222;
        int expectedIndex = -1;
        int actualIndex = findIndex(array, value);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testLargeArray() {
        Integer[] array = new Integer[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        int value = 999999;
        int expectedIndex = 999999;
        int actualIndex = findIndex(array, value);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testSmallArray() {
        Integer[] array = {4, 2, 3};
        int value = 2;
        int expectedIndex = 1;
        int actualIndex = ParallelSearchIndex.findIndex(array, value);
        assertEquals(expectedIndex, actualIndex);
    }

}

