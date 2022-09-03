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
                                {
                    "gridSize": 4,
                    "zombie": {
                        "x": 3,
                        "y": 1
                    },
                    "creatures": [
                        {
                            "x": 0,
                            "y": 1
                        },
                        {
                            "x": 1,
                            "y": 2
                        },
                        {
                            "x": 1,
                            "y": 1
                        }
                    ],
                    "commands": "RDRU"
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


    @Test
    void getCorrectResponse() throws Exception {
        this.mockMvc.perform(post("/getValue").contentType(MediaType.APPLICATION_JSON).content(payload))
                // FIXME: still not ideal. Should compare json objects.
                .andExpect(MockMvcResultMatchers.content().string("""
                        {"creatures":[],"zombies":[{"x":3,"y":1},{"x":3,"y":2},{"x":2,"y":1},{"x":1,"y":1}]}"""));
    }
}