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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // Runs only the web context (no TestRestTemplate)
@AutoConfigureMockMvc //  Automatically configures MockMvc
class RobotControllerIT {

    @Autowired
    private MockMvc mockMvc; // Injects MockMvc to test APIs

    @Autowired
    private RobotService robotService; // Ensures service is available

    @BeforeEach
    void setUp() {
        // Nothing required for MockMvc setup
    }

    @Test
    @DisplayName("Test valid simulation request")
    void testSimulateEndpoint() throws Exception {
        // Given (Valid Input)
        String input = "5 5\n1 2 S\nMRMLM";

        // When & Then (MockMvc performs a POST request and expects a 200 OK response)
        mockMvc.perform(post("/robot/simulate")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(input))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(content().string("3,1,S")); // Expect the correct robot position
    }

    @Test
    @DisplayName("Test invalid input format should return 400")
    void testInvalidInput() throws Exception {
        // Given (Invalid Input)
        String invalidInput = "5 5\n1 2"; // Missing command line

        // When & Then (MockMvc performs a POST request and expects a 400 Bad Request response)
        mockMvc.perform(post("/robot/simulate")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(invalidInput))
                .andExpect(status().isBadRequest()); // Expect HTTP 400 Bad Request
    }
}
