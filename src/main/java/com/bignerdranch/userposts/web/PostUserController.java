package com.bignerdranch.userposts.web;

import com.bignerdranch.userposts.dto.PostUserDto;
import com.bignerdranch.userposts.model.PostUser;
import com.bignerdranch.userposts.service.PostUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PostUserController {

    @Autowired
    private PostUserService postUserService;

    @PostMapping("/postusers/create")
    public ResponseEntity<PostUser> createPostUser(@Valid @RequestBody PostUserDto userDto) {
        PostUser postUser = new PostUser();
        postUser.setName(userDto.getName());
        postUser.setExpertise(userDto.getExpertise());
        postUser.setEmail(userDto.getEmail());

        // create operation
        PostUser createdUser = postUserService.savePostUser(postUser);
        return new ResponseEntity<PostUser>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/postusers")
    public List<PostUser> fetchAllPostUsers() {
        return postUserService.fetchAll();
    }
    @DeleteMapping("/postusers/{postUserId}")
    public void deletePostUser(@PathVariable("postUserId") Long postUserId) {
          postUserService.deletePostUser(postUserId);
    }

    @PutMapping("/postusers/update/{postUserId}")
    public ResponseEntity<PostUser> updatePostUser(@Valid @RequestBody PostUserDto userDto, @PathVariable("postUserId") Long postUserId) {
        PostUser postUser = postUserService.updatePostUser(userDto, postUserId);
        if (postUser!=null) {
            return new ResponseEntity<>(postUser, HttpStatus.OK);
        }
        log.debug("Invalid postUserId passed:{}", postUserId);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
