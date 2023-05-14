package com.geekster.instagram.controllers;

import com.geekster.instagram.dto.SignInInput;
import com.geekster.instagram.dto.SignInOutput;
import com.geekster.instagram.dto.SignUpInput;
import com.geekster.instagram.dto.SignUpOutput;
import com.geekster.instagram.models.Post;
import com.geekster.instagram.services.AuthenticationService;
import com.geekster.instagram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authService;
    @PostMapping("/signUp")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpInput){
        return userService.signUp(signUpInput);
    }

    @PostMapping("/signIn")
    public SignInOutput signIn(@RequestBody SignInInput signInInput){
        return userService.signIn(signInInput);
    }
    @PutMapping("/userId/{userId}")
    public ResponseEntity<String>modifyUserByUserId(@PathVariable Long userId,@RequestParam String userPhoneNo, @RequestParam String token,@RequestParam String userEmail){


        String message;
        HttpStatus status;
        if(authService.authenticate(token,userEmail)){
            userService.updateUserByUserId(userId,userPhoneNo);
            status = HttpStatus.OK;
            message = "User is updated successfully";

        }
        else{
            status = HttpStatus.FORBIDDEN;
            message = "User is not valid";
        }
        return new ResponseEntity<String>(message,status);
    }

}
