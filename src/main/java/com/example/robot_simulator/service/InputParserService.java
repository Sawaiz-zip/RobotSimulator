package com.example.robot_simulator.service;

import com.example.robot_simulator.model.Direction;
import com.example.robot_simulator.model.Robot;
import com.example.robot_simulator.model.Table;
import org.springframework.stereotype.Service;

@Service
public class InputParserService {

    public Table parseTable(String line) {
        String[] parts =  line.split(" ");
        System.out.println(parts[0] + "rows and columns " + parts[1]);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid table size format.");
        }
        int rows = Integer.parseInt(parts[0]);
        int cols = Integer.parseInt(parts[1]);
        return new Table(rows, cols);
    }

    public Robot parseRobot(String line) {
        String[] parts =  line.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid robot position format.");
        }
        int row = Integer.parseInt(parts[0]);
        int column  = Integer.parseInt(parts[1]);
        Direction direction = Direction.valueOf(parts[2]);
        return new Robot(row, column, direction);
    }

    public String parseCommand(String line) {
    return line.trim();
    }

}
