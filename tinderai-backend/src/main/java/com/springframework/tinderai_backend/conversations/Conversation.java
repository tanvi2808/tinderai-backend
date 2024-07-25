package com.springframework.tinderai_backend.conversations;

import java.util.List;

public record Conversation(
        String id,
        List<ChatMessage> messages
) {
}
