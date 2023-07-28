package com.anurag.BloggingPlatformAPI.service;

import com.anurag.BloggingPlatformAPI.model.*;

import com.anurag.BloggingPlatformAPI.model.dto.SignUpOutput;
import com.anurag.BloggingPlatformAPI.repository.IUserRepo;
import com.anurag.BloggingPlatformAPI.service.utility.HashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;


@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;



    public SignUpOutput signUpUser(User user) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());


            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }


    public String deleteUser(String email, String password,Long userId) throws NoSuchAlgorithmException {
        User user = userRepo.findFirstByUserEmail(email);

        if(user==null){
            return "Invalid Email";
        }


        String encryptedPassword = PasswordEncrypter.encryptPassword(password);

        if(encryptedPassword.equals(user.getUserPassword())){
            userRepo.deleteById(userId);
            return "Deleted Successfully";
        }

        return "Invalid Credentials";
    }
}




