package ru.job4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T valueToSearch;
    private final int start;
    private final int end;

    public ParallelSearchIndex(T[] array, T valueToSearch, int start, int end) {
        this.array = array;
        this.valueToSearch = valueToSearch;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= 10) {
            return lineFindIndex();
        }
        int mid = start + (end - start) / 2;
        ParallelSearchIndex<T> leftSearch = new ParallelSearchIndex<>(array, valueToSearch, start, mid);
        ParallelSearchIndex<T> rightSearch = new ParallelSearchIndex<>(array, valueToSearch, mid, end);
        leftSearch.fork();
        rightSearch.fork();
        int rightResult = rightSearch.join();
        int leftResult = leftSearch.join();

        return joinResult(rightResult, leftResult, mid);
    }

    public static <T> Integer findIndex(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearchIndex<>(array, value, 0, array.length));
    }

    private int lineFindIndex() {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(valueToSearch)) {
                return i;
            }
        }
        return -1;
    }

    private int joinResult(int rightResult, int leftResult, int mid) {
        if (leftResult != -1) {
            return leftResult;
        } else if (rightResult != -1) {
            return mid + rightResult;
        } else {
            return -1;
        }
    }
}