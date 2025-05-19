package com.api.rest_api.repository;

import com.api.rest_api.model.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, String> {
    MongoUser findByEmail(String email);
    void deleteByEmail(String email);
}