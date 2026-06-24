package com.taskflow.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TaskControllerIT extends BaseIntegrationTest {

    @Test
    void contextLoads() {
    }

    @Test
    void shouldGetTasks() throws Exception {
        mockMvc.perform(get("/api/v1/tasks"))
                .andDo(print());
    }

    @Test
    void shouldCreateTask() throws Exception {
        // your existing code
    }

    @Test
    void shouldReturn401ForDeleteWithoutToken() throws Exception {

        mockMvc.perform(delete("/api/v1/tasks/1"))
                .andExpect(status().isUnauthorized());
    }
}