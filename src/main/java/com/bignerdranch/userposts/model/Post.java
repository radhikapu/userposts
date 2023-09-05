package com.bignerdranch.userposts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table
public class Post {
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="postUserId")
    @JsonIgnore
    private PostUser postUser;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    private String title;
    private String body;

    public PostUser getPostUser() {
        return postUser;
    }

    public void setPostUser(PostUser postUser) {
        this.postUser = postUser;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
