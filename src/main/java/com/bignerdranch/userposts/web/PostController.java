package com.bignerdranch.userposts.web;

import com.bignerdranch.userposts.dto.PostDto;
import com.bignerdranch.userposts.model.Post;
import com.bignerdranch.userposts.service.PostService;
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
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostUserService postUserService;

    @PostMapping("/posts/create")
    public ResponseEntity<Post> createPost(@Valid  @RequestBody PostDto postDto) {
        //user should exist before creating the post else throw bad request
            Post post = postService.savePost(postDto);

        if (post != null ) {
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }
        log.debug("Invalid userId passed:{}", postDto.getUserId());
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/posts")
    public List<Post> fetchPosts() {
        return postService.fetchPosts();
    }

    @GetMapping("/posts/{postid}")
    public ResponseEntity<Post> fetchPostById(@PathVariable("postid") Long postId) {
        Post post = postService.fetchPostByPostId(postId);
        if (post!=null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        log.debug("Invalid postId passed:{}", postId);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/posts/postuser/{postuserid}")
    public List<Post> fetchByPostUserId(@PathVariable("postuserid") Long postuserid) {
          return postService.getPostsByPostUserId(postuserid);
    }
    @PutMapping("/posts/update/{postId}")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody PostDto postDt0, @PathVariable("postId") Long postId) {
        Post post = postService.updatePost(postDt0, postId);
        if (post!=null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        log.debug("Invalid postId passed:{}", postId);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/posts/{postid}")
    public ResponseEntity<String> deleteByPostId(@PathVariable("postid") Long postId){
            postService.deletePostById(postId);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
