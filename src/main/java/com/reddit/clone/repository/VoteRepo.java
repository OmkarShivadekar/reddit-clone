package com.reddit.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddit.clone.model.Vote;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long>{

}
