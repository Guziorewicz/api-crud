package com.api.rest_api;

import com.api.rest_api.model.PostgresUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.api.rest_api.model.MongoUser;
import com.api.rest_api.repository.MongoUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class MongoUserControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MongoUserRepository mongoUserRepository;

    private MongoUser savedUser;

    @BeforeEach
    void setup() {
        mongoUserRepository.deleteAll();
        savedUser = mongoUserRepository.save(new MongoUser("Alice", "alice@example.com", 33, "admin"));
    }

    @Test
    void testGetAllMongoUsers() throws Exception{
        mockMvc.perform(get("/api/mongo/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAddMongoUser() throws Exception {

        MongoUser newUser = new MongoUser("Ben", "ben@example.com", 10, "user");

        mockMvc.perform(post("/api/mongo/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ben"))
                .andExpect(jsonPath("$.email").value("ben@example.com"));
    }

    @Test
    void testEditMongoUser() throws Exception {

        MongoUser editedUser = new MongoUser("Alicey", "alice@example.com", 34, "dev");

        mockMvc.perform(put("/api/mongo/users/email/" + savedUser.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alicey"));
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/mongo/users/email/" + savedUser.getEmail()))
                .andExpect(status().isOk());
    }
}