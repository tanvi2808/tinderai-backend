package com.springframework.tinderai_backend;

import com.springframework.tinderai_backend.profiles.Gender;
import com.springframework.tinderai_backend.profiles.Profile;
import com.springframework.tinderai_backend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderaiBackendApplication implements CommandLineRunner {

	@Autowired
	ProfileRepository profileRepository;
	public static void main(String[] args) {
		SpringApplication.run(TinderaiBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Profile profile = new Profile(
				null,
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

	}
}
