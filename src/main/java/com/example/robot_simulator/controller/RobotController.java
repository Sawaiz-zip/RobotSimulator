package com.example.robot_simulator.controller;

import com.example.robot_simulator.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/robot")
public class RobotController {

    private final RobotService robotService;

    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<Object> simulate(@RequestBody String input) {
        return ResponseEntity.ok(robotService.startSimulation(input));
    }

    @PostMapping("/simulate/file")
    public ResponseEntity<Object> simulateFile(@RequestParam("file") MultipartFile file) {
        try {
            // Read file content as String
            String fileContent = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            return ResponseEntity.ok(robotService.startSimulation(fileContent));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error reading file: " + e.getMessage());
        }
    }
}

