package com.geekster.instagram.services;

import com.geekster.instagram.dto.SignInInput;
import com.geekster.instagram.dto.SignInOutput;
import com.geekster.instagram.dto.SignUpInput;
import com.geekster.instagram.dto.SignUpOutput;
import com.geekster.instagram.models.AuthenticationToken;
import com.geekster.instagram.models.Post;
import com.geekster.instagram.models.User;
import com.geekster.instagram.repositories.IUserRepo;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService tokenService;


    public SignUpOutput signUp(SignUpInput signUpInput) {
        User user = userRepo.findFirstByUserEmail(signUpInput.getUserEmail());

        if(user != null){
            throw new IllegalStateException("User already exist!!! Sign in instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpInput.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        user = new User(signUpInput.getUserFirstName(),signUpInput.getUserLastName(),signUpInput.getUserName(),
                signUpInput.getUserEmail(),encryptedPassword,signUpInput.getUserPhoneNumber(),Integer.parseInt(signUpInput.getUserAge()));

        AuthenticationToken token = new AuthenticationToken(user);
        tokenService.save(token);

        return new SignUpOutput("User Registered","User created successfully");

    }

    public String encryptPassword(String userPassword) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();
        String  hash = DatatypeConverter.printHexBinary(digested);
        return  hash;
    }

    public SignInOutput signIn(SignInInput signInInput) {

        User user = userRepo.findFirstByUserEmail(signInInput.getUserEmail());
        if(user==null){
            throw new IllegalStateException("User invalid!!!!...sign up");
        }


        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInInput.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        boolean isPasswordValid = encryptedPassword.equals(user.getUserPassword());

        if(!isPasswordValid){
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        AuthenticationToken authToken = tokenService.getToken(user);

        return new SignInOutput("Authentication Successful",authToken.getToken());

    }
    @Transactional
    public void updateUserByUserId(Long userId,String userPhoneNo) {
        userRepo.updateUserByUserId(userId,userPhoneNo);
    }

    public List<Post> getAllPostsByUserName(String userName) {
        User user  = userRepo.findByUserName(userName);
        List<Post>allPosts = user.getUserPosts();
        return allPosts;
    }

}
