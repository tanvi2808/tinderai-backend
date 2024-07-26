package com.springframework.tinderai_backend.conversations;

import java.util.List;

public interface ConversationService {

    public Conversation addNewConversation(ConversationRequest conversationRequest);

    Conversation addNewMessageToConv(String conversationId,
                                     ChatMessage chatMessage);

    List<Conversation> getAllConversations();
    Conversation getConversationById(String conversationId);
}
