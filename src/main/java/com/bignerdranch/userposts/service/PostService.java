package com.bignerdranch.userposts.service;

import com.bignerdranch.userposts.dto.PostDto;
import com.bignerdranch.userposts.model.Post;
import com.bignerdranch.userposts.model.PostUser;
import com.bignerdranch.userposts.repository.PostRepositoy;
import com.bignerdranch.userposts.repository.PostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepositoy postRepositoy;

    @Autowired
    private PostUserRepository postUserRepository;

    public Post savePost(PostDto postDto) {

        Optional<PostUser> postUser = postUserRepository.findById(postDto.getUserId());

        if (postUser.isPresent()) {
            Post post = new Post();
            post.setPostUser(postUser.get());
            post.setTitle(postDto.getTitle());
            post.setBody(postDto.getBody());

            return postRepositoy.save(post);
        }
        return null;
    }


    public List<Post> fetchPosts()
    {
        return (List<Post>)
                postRepositoy.findAll();
    }

    public Post fetchPostByPostId(Long postId)
    {

        return postRepositoy.findById(postId).get();
    }


    public List<Post> getPostsByPostUserId(long userId) {
        List<Post> posts = new ArrayList<>();
        postRepositoy.findByPostUserId(userId)
                .forEach(posts::add);

        return posts;
    }
    public void deletePostById(long postId){
         postRepositoy.deleteById(postId);
    }

    public Post updatePost(PostDto postDto, long postId) {
        Optional<Post> post = postRepositoy.findById(postId);
        // we are restricting not to allow the userid to be updated

        if (post.isPresent()) {
            post.get().setTitle(postDto.getTitle());
            post.get().setBody(postDto.getBody());

            return postRepositoy.save(post.get());
        }
        return null;
    }

}
