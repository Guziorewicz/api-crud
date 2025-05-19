package com.api.rest_api.controller;

import com.api.rest_api.model.PostgresUser;
import com.api.rest_api.repository.PostgresUserRepository;
import com.api.rest_api.service.PostgresUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/postgres/users")
public class PostgresUserController {

    private final PostgresUserService service;

    public PostgresUserController(PostgresUserService service) {
        this.service = service;
    }

    @GetMapping
    public List<PostgresUser> all() {
        return service.findAll();
    }

    @GetMapping("/email/{email}")
    public PostgresUser byId(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PostMapping
    public PostgresUser create(@RequestBody PostgresUser user) {
        return service.save(user);
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<PostgresUser> updateByEmail(@PathVariable String email, @RequestBody PostgresUser updated) {
        PostgresUser existing = service.findByEmail(email);
        if (existing == null) return ResponseEntity.notFound().build();

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setAge(updated.getAge());
        existing.setRole(updated.getRole());

        return ResponseEntity.ok(service.save((existing)));
    }


    @DeleteMapping("/email/{email}")
    public void delete(@PathVariable String email) {
        service.deleteByEmail(email);
    }

}