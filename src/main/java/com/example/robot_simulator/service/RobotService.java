package com.example.robot_simulator.service;

import com.example.robot_simulator.exception.InvalidCommandException;
import com.example.robot_simulator.exception.InvalidPositionException;
import com.example.robot_simulator.model.Robot;
import com.example.robot_simulator.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotService {

    private Robot robot;
    private Table table;
    private final InputParserService inputParserService;

    @Autowired
    public RobotService(InputParserService inputParserService) {
        this.inputParserService = inputParserService;
    }

    public String startSimulation(String[] input) {

        // Parse table size and robot initial position
        this.table = inputParserService.parseTable(input[0]);
        this.robot = inputParserService.parseRobot(input[1]);

        if (table == null || robot == null) {
            throw new IllegalStateException(
                    "Table and Robot must be initialized before executing commands."
            );
        }
        // Extracting commands from input
        String commands = inputParserService.parseCommand(input[2]);
        return runCommands(commands);
    }

    public String runCommands(String input) {

        // Process each command character in the input string
        for (char command : input.toCharArray()) {
            switch (command) {
                case 'M':
                    robot.move();
                    break;
                case 'R':
                    robot.rotateRight();
                    break;
                case 'L':
                    robot.rotateLeft();
                    break;
                default:
                    throw new InvalidCommandException("Invalid command: " + command);
            }
        }
        // Check if the robot's final position is within the table bounds
        if (table.isValidPosition(robot.getRow(), robot.getColumn())) {
            return robot.getPosition();
        } else {
            throw new InvalidPositionException("Movement out of bounds.");
        }
    }
}
