package com.springframework.tinderai_backend.conversations;

import java.util.List;

public record ConversationRequest(
        String profile_id,
        List<ChatMessage> messages
) {
}
