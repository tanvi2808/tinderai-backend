package com.springframework.tinderai_backend.conversations;

import com.springframework.tinderai_backend.profiles.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Conversation addNewMessageToConv(String conversationId, ChatMessage chatMessage) {

        Conversation conversation =
        conversationRepository.findById(conversationId).orElseThrow(() ->
                new ResponseStatusException((HttpStatus.NOT_FOUND), "No such conversation with id:"
                + conversationId + " found"));

        profileRepository.findById(chatMessage.profile_id()).orElseThrow(() ->
                new ResponseStatusException((HttpStatus.NOT_FOUND),"No conversation with this profile id found"));

        ChatMessage chatMessageNew = new ChatMessage(chatMessage.message(),
                chatMessage.profile_id(), LocalDateTime.now());
        conversation.messages().add(chatMessageNew);

        conversationRepository.save(conversation);

        return conversation;

    }

    @Override
    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }

    @Override
    public Conversation getConversationById(String conversationId) {
        System.out.println("convid="+conversationId);
        Conversation conversation =
                conversationRepository.findById(conversationId).orElseThrow(() ->
                        new ResponseStatusException((HttpStatus.NOT_FOUND), "No such conversation with id:"
                                + conversationId + " found"));
        return conversation;
    }
}
