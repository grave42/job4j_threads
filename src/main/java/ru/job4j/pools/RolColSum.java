package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        for (int i = 0; i < n; i++) {
            sums[i] = new Sums();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sums[i].rowSum += matrix[i][j];
                sums[j].colSum += matrix[i][j];
            }
        }

        return sums;
    }

    public static CompletableFuture<Sums[]> asyncSum(int[][] matrix) {
        return CompletableFuture.supplyAsync(() -> sum(matrix));
    }
}