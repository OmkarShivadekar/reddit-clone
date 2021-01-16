package com.reddit.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reddit.clone.model.VerificationToken;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {

}
