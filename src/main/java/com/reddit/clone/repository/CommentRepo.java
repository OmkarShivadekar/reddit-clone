package com.reddit.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddit.clone.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

}
