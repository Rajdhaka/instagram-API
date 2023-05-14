package com.geekster.instagram.controllers;

import com.geekster.instagram.models.Post;
import com.geekster.instagram.services.AuthenticationService;
import com.geekster.instagram.services.PostService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    AuthenticationService authService;

    @PostMapping("token/{token}/userEmail/{userEmail}")
    public ResponseEntity<String> savePost(@RequestBody Post post, @PathVariable String token , @PathVariable String userEmail){

        String message;
        HttpStatus status;
        if(authService.authenticate(token,userEmail)){
            postService.savePost(post);
            status = HttpStatus.OK;
            message = "post has been uploaded successfully";

        }
        else{
            status = HttpStatus.FORBIDDEN;
            message = "User is not valid";
        }
        return new ResponseEntity<String>(message,status);

    }
    @GetMapping("/all")
    public Iterable<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/userName/{userName}")
    public ResponseEntity<List<Post>>getAllPostsByUserName(@PathVariable String userName, @RequestParam String token,@RequestParam String userEmail){
        List<Post>allPosts= null;
        HttpStatus status;
        if(authService.authenticate(token,userEmail)){
            allPosts = postService. getAllPostsByUserName(userName);
            status = HttpStatus.OK;

        }
        else{
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<List<Post>>(allPosts,status);
    }

}
