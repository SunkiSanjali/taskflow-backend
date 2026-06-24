package com.taskflow.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerIT extends BaseIntegrationTest {

    @Test
    void shouldRegisterUser() throws Exception {

        String requestBody = """
                {
                  "name":"Test User",
                  "email":"test@test.com",
                  "password":"password123",
                  "role":"MEMBER"
                }
                """;

        mockMvc.perform(
                        post("/api/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldLoginUser() throws Exception {

        String registerBody = """
                {
                  "name":"Login User",
                  "email":"login@test.com",
                  "password":"password123",
                  "role":"MEMBER"
                }
                """;

        mockMvc.perform(
                post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerBody)
        );

        String loginBody = """
                {
                  "email":"login@test.com",
                  "password":"password123"
                }
                """;

        mockMvc.perform(
                        post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(loginBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }
}