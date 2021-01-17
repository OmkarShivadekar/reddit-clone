package com.reddit.clone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reddit.clone.model.VerificationToken;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
	
	Optional<VerificationToken> findByToken(String token);

}
