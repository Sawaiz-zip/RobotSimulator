package com.example.robot_simulator.model;

public enum Direction {
    N, E, S, W;

    public Direction rotateRight() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    public Direction rotateLeft() {
        return values()[(this.ordinal() + 3) % values().length];  // Check later with -1
    }

}
