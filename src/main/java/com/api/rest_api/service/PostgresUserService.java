package com.api.rest_api.service;

import org.springframework.transaction.annotation.Transactional;
import com.api.rest_api.model.PostgresUser;
import com.api.rest_api.repository.PostgresUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostgresUserService {
    private final PostgresUserRepository repository;

    public PostgresUserService(PostgresUserRepository repository) {
        this.repository = repository;
    }

    public List<PostgresUser> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public PostgresUser findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public PostgresUser save(PostgresUser user) {
        return repository.save(user);
    }

    @Transactional
    public void deleteByEmail(String email) {
        repository.deleteByEmail(email);
    }
}