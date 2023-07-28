package com.anurag.BloggingPlatformAPI.controller;


import com.anurag.BloggingPlatformAPI.model.Post;
import com.anurag.BloggingPlatformAPI.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("createPost")
    public String createPost(@RequestBody Post post, @RequestParam String email,@RequestParam String password) throws NoSuchAlgorithmException {
        return postService.createPost(post,email,password);
    }


    @GetMapping("getPost")
    public Post getPost(@RequestParam Long postId){
        return postService.getPost(postId);
    }

    @PutMapping("updatePostTitle")
    public String updatePost(@RequestParam String email,@RequestParam String password,@RequestParam Long postId,@RequestParam String postTitle) throws NoSuchAlgorithmException {
        return postService.updatePost(email,password,postId,postTitle);
    }

    @DeleteMapping("deletePost")
    public String deletePost(@RequestParam String email,@RequestParam String password,@RequestParam Long postId) throws NoSuchAlgorithmException {
        return postService.deletePost(email,password,postId);
    }
}
