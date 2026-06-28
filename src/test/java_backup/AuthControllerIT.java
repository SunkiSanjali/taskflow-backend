package com.taskflow.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerIT extends BaseIntegrationTest {

    @Test
    void shouldRegisterUser() throws Exception {

        String body = """
        {
            "email": "test@gmail.com",
            "password": "password123",
            "role": "USER"
        }
        """;

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    void shouldLoginUser() throws Exception {

        // register first inside SAME test (correct approach)
        String register = """
        {
            "email": "login@gmail.com",
            "password": "password123",
            "role": "USER"
        }
        """;

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register))
                .andExpect(status().isOk());

        String login = """
        {
            "email": "login@gmail.com",
            "password": "password123"
        }
        """;

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(login))
                .andExpect(status().isOk());
    }
}