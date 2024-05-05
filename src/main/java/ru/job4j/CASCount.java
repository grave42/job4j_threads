package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;


@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int reff;
        int temp;
        do {
            reff = count.get();
            temp = reff + 1;
        } while (!count.compareAndSet(reff, temp));
    }

    public int get() {
        return count.get();
    }
}