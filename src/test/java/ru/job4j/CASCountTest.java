package ru.job4j;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class CASCountTest {

    @Test
    void thatIncrementValueSuccess() {
        CASCount casCount = new CASCount();
        casCount.increment();
        casCount.increment();
        int expected = 2;
        assertEquals(expected, casCount.get());
    }

    @Test
    public void getMethodReturnsCurrentCount() {
        CASCount casCount = new CASCount();
        int count = casCount.get();
        assertEquals(0, count);
    }

    @Test
    public void multipleThreadsCanCallIncrementMethodSafely() {
        CASCount casCount = new CASCount();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                casCount.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                casCount.increment();
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(2000, casCount.get());
    }
}