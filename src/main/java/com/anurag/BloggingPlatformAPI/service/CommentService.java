package com.anurag.BloggingPlatformAPI.service;

import com.anurag.BloggingPlatformAPI.model.Comment;
import com.anurag.BloggingPlatformAPI.model.Post;
import com.anurag.BloggingPlatformAPI.model.User;
import com.anurag.BloggingPlatformAPI.repository.ICommentRepo;
import com.anurag.BloggingPlatformAPI.repository.IPostRepo;
import com.anurag.BloggingPlatformAPI.repository.IUserRepo;
import com.anurag.BloggingPlatformAPI.service.utility.HashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService{
    @Autowired
    ICommentRepo commentRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IPostRepo postRepo;
    
    

    public String postComment(String commentBody, Long postId, String email, String password) throws NoSuchAlgorithmException {
        User user = userRepo.findFirstByUserEmail(email);

        if (user == null) {
            return "Invalid Email";
        }

        Post post = postRepo.findById(postId).orElse(null);

        if (post == null) {
            return "Invalid Post";
        }

        String encryptedPassword = PasswordEncrypter.encryptPassword(password);

        Comment comment = new Comment();

        if (encryptedPassword.equals(user.getUserPassword())) {

            comment.setCommentBody(commentBody);
            comment.setBlogPost(post);
            comment.setCommenter(user);
            return "Comment Successfully";
        }

        return "Invalid Credentials";
    }

    public List<String> getComment(Long postId) {
        Post post = postRepo.findById(postId).orElse(null);

        if (post == null) {
            return List.of("Invalid Post");
        }
        
        List<String> commentList = new ArrayList<>();

        for (Comment comment:commentRepo.findAll()) {
            if(comment.getBlogPost().getPostId().equals(postId)){
                commentList.add(comment.getCommentBody());
            }
        }

        if(commentList.size()==0){
            return List.of("No comments on this Post");
        }

        return commentList;

    }

    public String deleteComment(String email, String password, Long commentId) throws NoSuchAlgorithmException {
        User user = userRepo.findFirstByUserEmail(email);

        if (user == null) {
            return "Invalid Email";
        }

        String encryptedPassword = PasswordEncrypter.encryptPassword(password);

        if (encryptedPassword.equals(user.getUserPassword())) {

            commentRepo.deleteById(commentId);
            return "Comment deleted Successfully";
        }

        return "Invalid Credentials";
    }
}
