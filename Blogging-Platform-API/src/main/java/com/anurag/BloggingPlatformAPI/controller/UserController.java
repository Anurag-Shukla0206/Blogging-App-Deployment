package com.anurag.BloggingPlatformAPI.controller;

import com.anurag.BloggingPlatformAPI.model.Comment;
import com.anurag.BloggingPlatformAPI.model.Follow;
import com.anurag.BloggingPlatformAPI.model.Post;
import com.anurag.BloggingPlatformAPI.model.User;

import com.anurag.BloggingPlatformAPI.model.dto.SignUpOutput;
import com.anurag.BloggingPlatformAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Validated
@RestController
public class UserController {
    @Autowired
    UserService userService;





    @PostMapping("user/signup")
    public SignUpOutput signUpUser(@RequestBody User user)
    {
        return userService.signUpUser(user);
    }

    @DeleteMapping("delete")
    public String deleteUser(@RequestParam String email,@RequestParam String password,@RequestParam Long userId) throws NoSuchAlgorithmException {
        return userService.deleteUser(email,password,userId);
    }


}
