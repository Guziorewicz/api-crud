package com.api.rest_api.controller;

import com.api.rest_api.model.MongoUser;
import com.api.rest_api.service.MongoUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mongo/users")
public class MongoUserController {

    private final MongoUserService service;

    public MongoUserController(MongoUserService service) {
        this.service = service;
    }

    @GetMapping
    public List<MongoUser> all() {
        return service.findAll();
    }

    @GetMapping("/email/{email}")
    public MongoUser findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PostMapping
    public MongoUser create(@RequestBody MongoUser user) {
        return service.save(user);
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<MongoUser> updateByEmail(@PathVariable String email, @RequestBody MongoUser updated) {
        MongoUser existing = service.findByEmail(email);
        if (existing == null) return ResponseEntity.notFound().build();

        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setRole(updated.getRole());
        existing.setEmail(updated.getEmail());

        return ResponseEntity.ok(service.save(existing));
    }

    @DeleteMapping("/email/{email}")
    public void delete(@PathVariable String email) {
        service.deleteByEmail(email);
    }

}