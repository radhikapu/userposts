package com.bignerdranch.userposts.web;

import com.bignerdranch.userposts.dto.PostDto;
import com.bignerdranch.userposts.model.Post;
import com.bignerdranch.userposts.service.PostService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    @InjectMocks
    PostController postController;

    @Mock
    PostService postService;

    @Autowired
    private MockMvc mvc;

    @Test
    void test_createPost() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));
        PostDto postDto = new PostDto();
        postDto.setBody("body1");
        postDto.setTitle("title1");
        postDto.setUserId(1L);
        when(postService.savePost(any(PostDto.class))).thenReturn(getPostData());
        ResponseEntity<Post> responseEntity = postController.createPost(postDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void test_createPostException() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));
        PostDto postDto = new PostDto();
        postDto.setBody("body1");
        postDto.setTitle("title1");
        postDto.setUserId(1L);
        when(postService.savePost(any(PostDto.class))).thenReturn(null);
        ResponseEntity<Post> responseEntity = postController.createPost(postDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void test_fetchPostAll() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));
        when(postService.fetchPosts()).thenReturn(getAllPostData());
        List<Post> postAll = postController.fetchPosts();
        assertThat(postAll.size()).isEqualTo(2);

    }

    @Test
    void test_fetchPostById() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));

        when(postService.fetchPostByPostId(any(Long.class))).thenReturn(getPostData());
        ResponseEntity<Post> responseEntity = postController.fetchPostById(1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void test_fetchPostByIdException() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));

        when(postService.fetchPostByPostId(any(Long.class))).thenReturn(null);
        ResponseEntity<Post> responseEntity = postController.fetchPostById(1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void test_fetchByPostUserId() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));

        when(postService.getPostsByPostUserId(any(Long.class))).thenReturn(getAllPostData());
        List<Post> postList = postController.fetchByPostUserId(1L);
        assertThat(postList.size()).isEqualTo(2);
    }

    @Test
    void test_updatePost(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));
        PostDto postDto = new PostDto();
        postDto.setBody("body1");
        postDto.setTitle("title1");
        postDto.setUserId(1L);
        when(postService.updatePost(any(PostDto.class), any(Long.class))).thenReturn(getPostData());
        ResponseEntity<Post> responseEntity = postController.updatePost(postDto, 1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void test_updatePostException(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));
        PostDto postDto = new PostDto();
        postDto.setBody("body1");
        postDto.setTitle("title1");
        postDto.setUserId(1L);
        when(postService.updatePost(any(PostDto.class), any(Long.class))).thenReturn(null);
        ResponseEntity<Post> responseEntity = postController.updatePost(postDto, 1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void test_deleteByPostId() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));
        ResponseEntity<String> responseEntity = postController.deleteByPostId(1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private Post getPostData() {
        Post post = new Post();
        post.setTitle("title1");
        post.setBody("body1");
        return post;
    }

    private List<Post> getAllPostData() {
        List<Post> postAll = new ArrayList<>();
        Post post = new Post();
        post.setTitle("title1");
        post.setBody("body1");
        postAll.add(post);

        Post post1 = new Post();
        post1.setTitle("title1");
        post1.setBody("body1");
        postAll.add(post1);
        return postAll;
    }


}