package com.example.robot_simulator.model;

public class Robot {
    private int row;
    private int column;
    private Direction direction;

    public Robot(int row, int column, Direction direction) {
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void move() {
        switch (direction) {
            case N -> row--;
            case S -> row++;
            case E -> column++;
            case W -> column--;
        }
    }

    public void rotateRight() {
        direction = direction.rotateRight();
    }

    public void rotateLeft() {
        direction = direction.rotateLeft();
    }

    public String getPosition() {
        return row + "," + column + "," + direction.toString();
    }

}
