package com.geekster.instagram.services;

import com.geekster.instagram.models.Post;
import com.geekster.instagram.models.User;
import com.geekster.instagram.repositories.IPostRepo;
import com.geekster.instagram.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class PostService {
    @Autowired
    IPostRepo postRepo;
    @Autowired
    UserService userService;
    public void savePost(Post post) {
        postRepo.save(post);
    }

    public Iterable<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public List<Post> getAllPostsByUserName(String userName) {
        return userService.getAllPostsByUserName(userName);
    }
}
