package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        for (int i = 0; i < n; i++) {
            sums[i] = new Sums();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sums[i].setRowSum(sums[i].getRowSum() + matrix[i][j]);
                sums[j].setColSum(sums[j].getColSum() + matrix[i][j]);
            }
        }

        return sums;
    }

    public static CompletableFuture<Sums[]> asyncSum(int[][] matrix) {
        return CompletableFuture.supplyAsync(() -> sum(matrix));
    }
}