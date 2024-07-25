package com.springframework.tinderai_backend.conversations;

import java.time.LocalDateTime;

public record ChatMessage (
    String message,
    String profile_id,
    LocalDateTime messageTime
){

}
