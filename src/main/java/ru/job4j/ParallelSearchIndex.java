package ru.job4j;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchIndex extends RecursiveTask<Integer> {

    private final int[] array;
    private final int valueToSearch;

    public ParallelSearchIndex(int[] array, int valueToSearch) {
        this.array = array;
        this.valueToSearch = valueToSearch;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 10) {
            return lineFindIndex();
        }
        int mid = array.length / 2;
        ParallelSearchIndex leftSearch = new ParallelSearchIndex(Arrays.copyOfRange(array, 0, mid), valueToSearch);
        ParallelSearchIndex rightSearch = new ParallelSearchIndex(Arrays.copyOfRange(array, mid, array.length), valueToSearch);
        leftSearch.fork();
        rightSearch.fork();
        int rightResult = rightSearch.join();
        int leftResult = leftSearch.join();

        return joinResult(rightResult, leftResult, mid);
    }

    public static Integer findIndex(int[] array, int value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearchIndex(array, value));
    }

    private int lineFindIndex() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valueToSearch) {
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
