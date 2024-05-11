package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        RolColSum.Sums[] result = sum(matrix);
        int exeptedRow3 = 24;
        int exeptedCol3 = 18;
        assertEquals(exeptedRow3, result[2].getRowSum());
        assertEquals(exeptedCol3, result[2].getColSum());
    }

    @Test
    void thenSuccessAndCheck3RowColAsync() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        CompletableFuture<RolColSum.Sums[]> futureSums = asyncSum(matrix);
        RolColSum.Sums[] result = futureSums.get();
        int exeptedRow3 = 24;
        int exeptedCol3 = 18;
        assertEquals(exeptedRow3, result[2].getRowSum());
        assertEquals(exeptedCol3, result[2].getColSum());
    }
}