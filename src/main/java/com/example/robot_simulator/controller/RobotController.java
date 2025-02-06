package com.example.robot_simulator.controller;
import com.example.robot_simulator.service.RobotService;
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
        return ResponseEntity.ok(robotService.startSimulation(checkData(input)));
    }

    @PostMapping("/simulate/file")
    public ResponseEntity<Object> simulateFile(@RequestParam("file") MultipartFile file) {
        try {
            // Read file content as String
            String fileContent = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            return ResponseEntity.ok(robotService.startSimulation(checkData(fileContent)));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error reading file: " + e.getMessage());
        }
    }

    public String[] checkData(String input) {
        String[] parts = input.split("\n");
        if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Invalid input format. Expected 3 lines: (table, robot, commands)."
            );
        }
        return parts;
    }
}

