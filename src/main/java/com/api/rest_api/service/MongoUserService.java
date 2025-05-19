package com.api.rest_api.service;

import com.api.rest_api.model.MongoUser;
import com.api.rest_api.repository.MongoUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoUserService {
    private final MongoUserRepository repository;

    public MongoUserService(MongoUserRepository repository) {
        this.repository = repository;
    }

    public List<MongoUser> findAll() {
        return repository.findAll();
    }

    public MongoUser findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public MongoUser save(MongoUser user) {
        return repository.save(user);
    }

    public void deleteByEmail(String email) {
        repository.deleteByEmail(email);
    }
}