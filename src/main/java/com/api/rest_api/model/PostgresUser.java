package com.api.rest_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class PostgresUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private int age;
    private String role;

    public PostgresUser() {}

    public PostgresUser(String name, String email, int age, String role) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    // Getters and Setters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}

}