package com.bignerdranch.userposts.repository;

import com.bignerdranch.userposts.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositoy extends JpaRepository<Post, Long> {


    @Query(value = "SELECT p FROM Post p WHERE p.postUser.postUserId = ?1")
    public List<Post> findByPostUserId(long postUserId);

}
