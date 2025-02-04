package com.example.robot_simulator.model;

public class Table {

    private final int rows;
    private final int columns;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Table(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new IllegalArgumentException("Table must have at least one row and column");
        }
        this.rows = rows;
        this.columns = columns;
    }
    public boolean isValidPosition(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }


}

