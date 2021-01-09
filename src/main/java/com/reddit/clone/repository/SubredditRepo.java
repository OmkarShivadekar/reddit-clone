package com.reddit.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddit.clone.model.Subreddit;

@Repository
public interface SubredditRepo extends JpaRepository<Subreddit, Long>{

}
