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

    public String startSimulation(String input) {
        String[] parts = input.split("\n");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid input format.");
        }

        this.table = inputParserService.parseTable(parts[0]);
        System.out.println(table.getRows() + " and " + table.getColumns() + "printing values in table");
        this.robot = inputParserService.parseRobot(parts[1]);
        System.out.println(robot.getRow() + " and " + robot.getColumn() + " and " + robot.getPosition() + " printing values in robot");
        String commands = inputParserService.parseCommand(parts[2]);
        System.out.println(commands + "printing commands");

        return runCommands(commands);


    }

    public String runCommands(String input) {
    for (char command : input.toCharArray())
    {
        switch (command) {
            case 'M' : robot.move();
            break;
            case 'R' : robot.rotateRight();
            break;
            case 'L' : robot.rotateLeft();
            break;
            default  : throw new InvalidCommandException("Invalid command: " + command);
        }
    }
    System.out.println(robot.getPosition().toString() + "robot possition after running commands");
     if(table.isValidPosition(robot.getRow(), robot.getColumn()))
     {
         return robot.getPosition();
     }
     else {
         throw new InvalidPositionException("Invalid position.");
     }
    }

}
