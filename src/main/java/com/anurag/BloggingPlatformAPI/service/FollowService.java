package com.anurag.BloggingPlatformAPI.service;

import com.anurag.BloggingPlatformAPI.model.Follow;
import com.anurag.BloggingPlatformAPI.model.User;
import com.anurag.BloggingPlatformAPI.repository.IFollowRepo;
import com.anurag.BloggingPlatformAPI.repository.IUserRepo;
import com.anurag.BloggingPlatformAPI.service.utility.HashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService {
    @Autowired
    IFollowRepo followRepo;

    @Autowired
    IUserRepo userRepo;

    public String follow(String email, String password, String usernameToFollow) throws NoSuchAlgorithmException {
        User user = userRepo.findFirstByUserEmail(email);

        if (user == null) {
            return "Invalid Email";
        }

        String encryptedPassword = PasswordEncrypter.encryptPassword(password);

        if (encryptedPassword.equals(user.getUserPassword())) {

            userRepo.findFirstByUserName(usernameToFollow);
            return "Followed Successfully";
        }

        return "Invalid User";
    }

    public String unfollow(String email, String password, String usernameTounFollow) throws NoSuchAlgorithmException {
        User user = userRepo.findFirstByUserEmail(email);

        if (user == null) {
            return "Invalid Email";
        }

        String encryptedPassword = PasswordEncrypter.encryptPassword(password);

        if (encryptedPassword.equals(user.getUserPassword())) {

            userRepo.findFirstByUserName(usernameTounFollow);
            return "Unfollowed Successfully";
        }

        return "Invalid User";
    }

    public List<String> knowFollowers(String userName) {
        User user = userRepo.findFirstByUserName(userName);

        if (user == null) {
            return List.of("Invalid UserName");
        }

        List<String> followers = new ArrayList<>();

        for (Follow follow: followRepo.findAll()) {
            if(follow.getCurrentUser().getUserName().equals(userName)){
                followers.add(follow.getCurrentUserFollower().getUserName());
            }
        }
        if(followers.size()==0){
            return List.of("No Followers");
        }
        return followers;
    }

    public List<String> knowFollowing(String userName) {
        User user = userRepo.findFirstByUserName(userName);

        if (user == null) {
            return List.of("Invalid UserName");
        }

        List<String> following = new ArrayList<>();

        for (Follow follow: followRepo.findAll()) {
            if(follow.getCurrentUserFollower().getUserName().equals(userName)){
                following.add(follow.getCurrentUser().getUserName());
            }
        }
        if(following.size()==0){
            return List.of("No Following");
        }
        return following;
    }
}
