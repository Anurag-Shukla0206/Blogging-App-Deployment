package com.anurag.BloggingPlatformAPI.service;

import com.anurag.BloggingPlatformAPI.model.Post;
import com.anurag.BloggingPlatformAPI.model.User;
import com.anurag.BloggingPlatformAPI.repository.IPostRepo;
import com.anurag.BloggingPlatformAPI.repository.IUserRepo;
import com.anurag.BloggingPlatformAPI.service.utility.HashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class PostService {
    @Autowired
    IPostRepo postRepo;

    @Autowired
    IUserRepo userRepo;



    public String createPost(Post post, String email, String password) throws NoSuchAlgorithmException {
        User user = userRepo.findFirstByUserEmail(email);

        if(user==null){
            return "Invalid Email";
        }

        String encryptedPassword = PasswordEncrypter.encryptPassword(password);

        if(encryptedPassword.equals(user.getUserPassword())){
            postRepo.save(post);
            return "Posted Successfully";
        }

        return "Invalid User";

    }


    public Post getPost(Long postId) {
        return postRepo.findById(postId).orElse(null);
    }

    public String updatePost(String email, String password, Long postId, String postTitle) throws NoSuchAlgorithmException {
        User user = userRepo.findFirstByUserEmail(email);

        if(user==null){
            return "Invalid Email";
        }

        Post post = postRepo.findById(postId).orElse(null);

        if (post==null){
            return "Invalid Post";
        }

        String encryptedPassword = PasswordEncrypter.encryptPassword(password);

        if(encryptedPassword.equals(user.getUserPassword()) && post.getPostOwner().getUserId().equals(user.getUserId())){
            post.setPostTitle(postTitle);
            postRepo.save(post);
            return "Updated Successfully";
        }

        return "Invalid Credentials";

    }

    public String deletePost(String email, String password, Long postId) throws NoSuchAlgorithmException {
        User user = userRepo.findFirstByUserEmail(email);

        if(user==null){
            return "Invalid Email";
        }

        Post post = postRepo.findById(postId).orElse(null);

        if (post==null){
            return "Invalid Post";
        }

        String encryptedPassword = PasswordEncrypter.encryptPassword(password);

        if(encryptedPassword.equals(user.getUserPassword()) && post.getPostOwner().getUserId().equals(user.getUserId())){
            postRepo.deleteById(postId);
            return "Deleted Post Successfully";
        }

        return "Invalid Credentials";
    }
}
