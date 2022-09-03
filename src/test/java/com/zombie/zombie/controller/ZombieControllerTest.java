package com.zombie.zombie.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class ZombieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String payload;

    @BeforeEach
    void setUp() {
        payload = """
                {"gridSize": 4, "commands": "UDRR", "zombie": {
                    "x": 1,
                    "y": 2
                },

                "creatures":[
                    {"x": 1, "y": 0},
                    {"x": 1, "y": 1}
                ]
                }
                                """;
    }

    @Test
    void getHttpStatusCode400NoPayload() throws Exception {
        this.mockMvc.perform(post("/getValue")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getHttpStatusCode4xxWrongPayload() throws Exception {
        this.mockMvc.perform(post("/getValue").contentType(MediaType.APPLICATION_JSON).contentType("hello world"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void getHttpStatusCode201() throws Exception {
        this.mockMvc.perform(post("/getValue").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}