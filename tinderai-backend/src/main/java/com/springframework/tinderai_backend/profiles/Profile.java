package com.springframework.tinderai_backend.profiles;

public record Profile(
        String id,
        String firstName,
        String lastName,
        String ethnicity,
        String age,
        String bio,
        Gender gender,
        String myersBriggsPersonalityType
) {
}
