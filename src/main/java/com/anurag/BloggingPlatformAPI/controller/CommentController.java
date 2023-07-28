package com.anurag.BloggingPlatformAPI.controller;

import com.anurag.BloggingPlatformAPI.model.Comment;
import com.anurag.BloggingPlatformAPI.model.Post;
import com.anurag.BloggingPlatformAPI.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("comment")
    public String postComment(@RequestParam String commentBody,
                              @RequestParam Long postId,
                              @RequestParam String email,
                              @RequestParam String password ) throws NoSuchAlgorithmException {
        return commentService.postComment(commentBody,postId,email,password);
    }

    @GetMapping("comment")
    public List<String> getComment(@RequestParam Long postId){
        return commentService.getComment(postId);
    }

    @DeleteMapping("deleteComment")
    public String deleteComment(@RequestParam String email,@RequestParam String password,
                                @RequestParam Long commentId) throws NoSuchAlgorithmException {
        return commentService.deleteComment(email,password,commentId);
    }
}
