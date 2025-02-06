package com.example.robot_simulator.controller;

import com.example.robot_simulator.service.RobotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RobotControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test valid simulation request")
    void testSimulateEndpoint() throws Exception {
        // Given (Valid Input)
        String input = "5 5\n1 2 S\nMRMLM";

        // When you send a valid input then expect a 200 OK response and final robot position
        mockMvc.perform(post("/robot/simulate")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(input))
                .andExpect(status().isOk())
                .andExpect(content().string("3,1,S")); // Expect the correct robot position
    }

    @Test
    @DisplayName("Test invalid input format should return 400")
    void testInvalidInput() throws Exception {
        // Given (Invalid Input)
        String invalidInput = "5 5\n1 2"; // Missing command line

        // When you send an invalid input then expect a 400 badRequest response.
        mockMvc.perform(post("/robot/simulate")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(invalidInput))
                .andExpect(status().isBadRequest());
    }
}
