package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static ru.job4j.pools.RolColSum.asyncSum;
import static ru.job4j.pools.RolColSum.sum;

class RolColSumTest {

    @Test
    void thenSuccessAndCheck3RowColNotAsync() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] result = sum(matrix);
        Sums[] expected = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18)
        };
        assertArrayEquals(expected, result);
    }

    @Test
    void thenSuccessAndCheck3RowColAsync() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        CompletableFuture<Sums[]> futureSums = asyncSum(matrix);
        Sums[] result = futureSums.get();
        Sums[] expected = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18)
        };
        assertArrayEquals(expected, result);
    }
}