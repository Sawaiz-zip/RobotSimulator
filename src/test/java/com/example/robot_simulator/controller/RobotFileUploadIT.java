package com.example.robot_simulator.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RobotFileUploadIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test valid file upload")
    void testFileUpload() throws Exception {
        // Given (Valid Input File)
        MockMultipartFile file = new MockMultipartFile(
                "file", "robot_input.txt", MediaType.TEXT_PLAIN_VALUE,
                "5 5\n1 2 S\nMRMLM".getBytes()
        );

        // When you send a valid input then expect a 200 OK response and final robot position
        mockMvc.perform(multipart("/robot/simulate/file")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("3,1,S")); // Expect the correct robot position
    }

    @Test
    @DisplayName("Test invalid file upload (empty file) should return 400")
    void testInvalidFileUpload() throws Exception {
        // Given (Invalid Empty File)
        MockMultipartFile emptyFile = new MockMultipartFile(
                "file", "empty.txt", MediaType.TEXT_PLAIN_VALUE, "".getBytes()
        );

        // When you send an invalid input then expect a 400 badRequest response.
        mockMvc.perform(multipart("/robot/simulate/file")
                        .file(emptyFile))
                .andExpect(status().isBadRequest()); // Expect HTTP 400 Bad Request
    }
}
