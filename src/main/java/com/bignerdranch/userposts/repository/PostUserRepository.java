package com.bignerdranch.userposts.repository;

import com.bignerdranch.userposts.model.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PostUserRepository extends JpaRepository<PostUser, Long> {

}
