package com.anurag.BloggingPlatformAPI.controller;

import com.anurag.BloggingPlatformAPI.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class FollowController {

    @Autowired
    FollowService followService;

    @PostMapping("follow")
    public String follow(@RequestParam String email,@RequestParam String password,@RequestParam String usernameToFollow) throws NoSuchAlgorithmException {
        return followService.follow(email,password,usernameToFollow);
    }

    @DeleteMapping("unfollow")
    public String unfollow(@RequestParam String email,@RequestParam String password,@RequestParam String usernameTounFollow) throws NoSuchAlgorithmException {
        return followService.unfollow(email,password,usernameTounFollow);
    }

    @GetMapping("knowFollowers")
    public List<String> knowFollowers(@RequestParam String userName){
        return followService.knowFollowers(userName);
    }

    @GetMapping("knowFollowing")
    public List<String> knowFollowing(@RequestParam String userName){
        return followService.knowFollowing(userName);
    }

}
