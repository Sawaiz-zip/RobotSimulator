package com.example.robot_simulator.service;

import com.example.robot_simulator.model.Direction;
import com.example.robot_simulator.model.Robot;
import com.example.robot_simulator.model.Table;
import org.springframework.stereotype.Service;

@Service
public class InputParserService {

    public Table parseTable(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Table size is missing.");
        }

        String[] parts = line.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid table size format.");
        }

        try {
            return new Table(
                    Integer.parseInt(parts[0]), // Rows
                    Integer.parseInt(parts[1])  // Columns
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Table size must be numeric.");
        }
    }

    public Robot parseRobot(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Robot position is missing.");
        }

        String[] parts = line.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Invalid robot position format. Correct format: row column direction(N, E, S, W)"
            );
        }

        try {
            return new Robot(
                    Integer.parseInt(parts[0]), // Rows
                    Integer.parseInt(parts[1]), // Columns
                    Direction.valueOf(parts[2]) // Direction
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Robot position must be numeric.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid direction. Allowed: N, E, S, W.");
        }
    }

    public String parseCommand(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Command sequence cannot be empty.");
        }
        return line.trim();
    }
}
