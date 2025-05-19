package com.api.rest_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class MongoUser {

    @Id
    private String id;

    private String name;
    private String email;
    private int age;
    private String role;

    public MongoUser() {}

    public MongoUser(String name, String email, int age, String role) {
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
