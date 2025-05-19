package com.api.rest_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.api.rest_api.model.PostgresUser;
import com.api.rest_api.repository.PostgresUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc

public class PostgresUserControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostgresUserRepository postgresUserRepository;

    private PostgresUser savedUser;

    @BeforeEach
    void setup() {
        postgresUserRepository.deleteAll();
        savedUser = postgresUserRepository.save(new PostgresUser("Alice", "alice@example.com", 30, "admin"));
    }

    @Test
    void testGetAllPostgresUsers() throws Exception {
        mockMvc.perform(get("/api/postgres/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetSpecificPostgresUser() throws Exception {
        mockMvc.perform(get("/api/postgres/users/email/" + savedUser.getEmail()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testAddPostgresUser() throws Exception {

        PostgresUser newUser = new PostgresUser("Ben", "ben@example.com", 10, "user");

        mockMvc.perform(post("/api/postgres/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ben"))
                .andExpect(jsonPath("$.email").value("ben@example.com"));
    }

    @Test
    void testEditPostgresUser() throws Exception {

        PostgresUser editedUser = new PostgresUser(null, "alice2@example.com", 34, "dev");

        mockMvc.perform(put("/api/postgres/users/email/" + savedUser.getEmail())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(editedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("alice2@example.com"));
    }

    @Test
    void testDeletePostgresUser() throws Exception {
        mockMvc.perform(delete("/api/postgres/users/email/" + savedUser.getEmail()))
                .andExpect(status().isOk());
    }
}