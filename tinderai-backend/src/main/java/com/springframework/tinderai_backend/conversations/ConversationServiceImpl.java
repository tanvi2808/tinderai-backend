package com.springframework.tinderai_backend.conversations;

import com.springframework.tinderai_backend.profiles.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public class ConversationServiceImpl implements ConversationService {

    ProfileRepository profileRepository;

    ConversationRepository conversationRepository;

    public ConversationServiceImpl(ProfileRepository profileRepository,
                                   ConversationRepository conversationRepository) {
        this.profileRepository = profileRepository;
        this.conversationRepository = conversationRepository;

    }


    @Override
    public Conversation addNewConversation(ConversationRequest conversationRequest) {
        profileRepository.findById(conversationRequest.profile_id()).orElseThrow(() -> new RuntimeException("not found"));

        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                new ArrayList<>());

        return conversationRepository.save(conversation);
    }
}
