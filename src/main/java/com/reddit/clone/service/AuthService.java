package com.reddit.clone.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reddit.clone.dto.RegisterRequest;
import com.reddit.clone.exception.SpringRedditException;
import com.reddit.clone.model.NotificationEmail;
import com.reddit.clone.model.User;
import com.reddit.clone.model.VerificationToken;
import com.reddit.clone.repository.UserRepo;
import com.reddit.clone.repository.VerificationTokenRepo;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class AuthService {
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserRepo userRepo;
	
	private final VerificationTokenRepo verifyRepo;
	
	private final MailService mailService;
	
	
	@Transactional
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		
		userRepo.save(user);
		
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please activate your account",
				user.getEmail(), "Thank you for signing up Reddit Clone"
						+ "\n Please click on the below url to activate your account \n"
						+ "http://localhost:8080/api/auth/accountVerification/"+token));
	}


	private String generateVerificationToken(User user) {

		String token = UUID.randomUUID().toString();
		
		VerificationToken verify = new VerificationToken();
		verify.setToken(token);
		verify.setUser(user);
		
		verifyRepo.save(verify);
		
		return token;
	}


	public void verifyAccount(String token) {
		
		Optional<VerificationToken> verificationToken = verifyRepo.findByToken(token);
		verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));
		fetchUserAndEnable(verificationToken.get());
	}


	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {

		String username = verificationToken.getUser().getUsername();
		User user = userRepo.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name - "+username));
		user.setEnabled(true);
		userRepo.save(user);
	}
}
