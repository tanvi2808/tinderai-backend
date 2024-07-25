package com.springframework.tinderai_backend.conversations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversationController {

    @Autowired
    ConversationService conversationService;

    @PostMapping("/conversations")
    public ResponseEntity<Conversation> createConversion(@RequestBody ConversationRequest conversationRequest){
        Conversation conversation ;
        try{
           conversation =  conversationService.addNewConversation(conversationRequest);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(conversation);

    }
}
