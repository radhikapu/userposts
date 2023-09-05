package com.bignerdranch.userposts.model;

import jakarta.persistence.*;

@Entity
@Table
public class PostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postUserId;
    private String name;
    private String email;
    private String expertise;

    public long getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(long postUserId) {
        this.postUserId = postUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

}
