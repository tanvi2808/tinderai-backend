package com.springframework.tinderai_backend;

import com.springframework.tinderai_backend.conversations.ChatMessage;
import com.springframework.tinderai_backend.conversations.Conversation;
import com.springframework.tinderai_backend.conversations.ConversationRepository;
import com.springframework.tinderai_backend.profiles.Gender;
import com.springframework.tinderai_backend.profiles.Profile;
import com.springframework.tinderai_backend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderaiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private ConversationRepository conversationRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderaiBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Profile profile = new Profile(
				"1",
				"Georgia",
				"Mary",
				"Sweden",
				"24",
				"Art lover",
				Gender.FEMALE,
				"ENTI"
		);

		profileRepository.save(profile);
		profileRepository.findAll().forEach(System.out::println);


		Conversation conversation = new Conversation("1",
				List.of(new ChatMessage(
						"Hell0", profile.id(),
						LocalDateTime.now()
				)));
		conversationRepository.save(conversation);
		conversationRepository.findAll().forEach(System.out::println);
	}
}
