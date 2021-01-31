package com.reddit.clone.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.repository.SubredditRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
	
	private final SubredditRepo subredditRepo;

	@Transactional
	public SubredditDto save(SubredditDto subredditDto) {
		Subreddit save = subredditRepo.save(mapSubredditDto(subredditDto));
		subredditDto.setId(save.getId());
		return subredditDto;
	}

	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<SubredditDto> getAll() {
		return subredditRepo.findAll()
			.stream()
			.map(this::mapToDto)
			.collect(Collectors.toList());
	}
	
	private Subreddit mapSubredditDto(SubredditDto subredditDto) {
		return Subreddit.builder().name(subredditDto.getName())
						   .description(subredditDto.getDescription())
						   .build();
	}

	private SubredditDto mapToDto(Subreddit subreddit) {
		return SubredditDto.builder().id(subreddit.getId())
					.name(subreddit.getName())
					.description(subreddit.getDescription())
					.numberOfPosts(subreddit.getPosts().size())
					.build();
	}

	
}
