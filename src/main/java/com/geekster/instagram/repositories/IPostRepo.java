package com.geekster.instagram.repositories;

import com.geekster.instagram.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post, Integer> {
    Post findByPostId(Integer postId);
}
