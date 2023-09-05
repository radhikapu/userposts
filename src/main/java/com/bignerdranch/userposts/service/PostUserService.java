package com.bignerdranch.userposts.service;

import com.bignerdranch.userposts.dto.PostUserDto;
import com.bignerdranch.userposts.model.PostUser;
import com.bignerdranch.userposts.repository.PostRepositoy;
import com.bignerdranch.userposts.repository.PostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostUserService {

    @Autowired
    private PostUserRepository postUserRepository;

    @Autowired
    private PostRepositoy postRepositoy;
    public PostUser savePostUser(PostUser postUser) {
        return postUserRepository.save(postUser);
    }

    public List<PostUser> fetchAll() {
        return postUserRepository.findAll();
    }

    public void deletePostUser(long postUserId) {
        postUserRepository.deleteById(postUserId);
    }

    public PostUser updatePostUser(PostUserDto postUserDto, long postUserId) {
        Optional<PostUser> postUser = postUserRepository.findById(postUserId);

        if (postUser.isPresent()) {
            postUser.get().setEmail(postUserDto.getEmail());
            postUser.get().setName(postUserDto.getName());
            postUser.get().setExpertise(postUserDto.getExpertise());

            return postUserRepository.save(postUser.get());
        }
        return null;
    }
}
