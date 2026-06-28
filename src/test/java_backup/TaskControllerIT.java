package com.taskflow.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TaskControllerIT extends BaseIntegrationTest {

    @Test
    void shouldGetTasks() throws Exception {

        mockMvc.perform(get("/api/v1/tasks"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateUpdateAndDeleteTaskFlow() throws Exception {

        // 1. CREATE TASK
        MvcResult result = mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "title": "Integration Task",
                            "description": "Test create"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // NOTE: If your API returns ID, extract it here
        // For now we assume ID = 1 OR service auto handles it

        // 2. UPDATE TASK
        mockMvc.perform(put("/api/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "title": "Updated Task",
                            "description": "Updated desc"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk());

        // 3. DELETE TASK
        mockMvc.perform(delete("/api/v1/tasks/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnUnauthorizedForDeleteWithoutToken() throws Exception {

        mockMvc.perform(delete("/api/v1/tasks/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}