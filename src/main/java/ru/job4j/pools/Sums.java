package ru.job4j.pools;

import java.util.Objects;

public class Sums {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Sums sums = (Sums) object;
        return rowSum == sums.rowSum && colSum == sums.colSum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowSum, colSum);
    }
}

