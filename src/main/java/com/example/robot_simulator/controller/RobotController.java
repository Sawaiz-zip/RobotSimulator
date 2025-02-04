package com.example.robot_simulator.controller;

import com.example.robot_simulator.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/robot")
public class RobotController {

    @Autowired
    private RobotService robotService;

    @PostMapping("/simulate")
    public String simulate(@RequestBody String input) {
        return robotService.startSimulation(input);
    }

//    @PostMapping("/simulate/file/upload")
//    public String simulateFileUpload(@MulipartForm File file) {
//        if file == null
//        String s = file.read;
//
//        return robotService.startSimulation(s);
//    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}
