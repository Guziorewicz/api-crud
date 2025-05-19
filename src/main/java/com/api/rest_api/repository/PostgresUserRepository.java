package com.api.rest_api.repository;

import com.api.rest_api.model.PostgresUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresUserRepository extends JpaRepository<PostgresUser, String> {
    PostgresUser findByEmail(String email);
    void deleteByEmail(String email);
}