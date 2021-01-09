package com.reddit.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddit.clone.model.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

}
