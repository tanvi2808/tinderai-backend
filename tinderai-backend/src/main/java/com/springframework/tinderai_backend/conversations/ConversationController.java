package com.springframework.tinderai_backend.conversations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @PostMapping("conversations/{conversationId}")
    public ResponseEntity<Conversation> addNewMessageToConv(@PathVariable String conversationId,
                                                            @RequestBody ChatMessage chatMessage) {
        Conversation conversation;
        try {
            conversation = conversationService.addNewMessageToConv(conversationId, chatMessage);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(conversation);
    }

    @GetMapping("/conversations")
    public ResponseEntity<List<Conversation>> getAllConversations() {

        List<Conversation> conversations;
        try {

            conversations = conversationService.getAllConversations();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(conversations);
    }

    @GetMapping("/conversations/{conversationId}")
    public ResponseEntity<Conversation> getConversationByID(@PathVariable String conversationId) {

        Conversation conversation;
        try {

            conversation = conversationService.getConversationById(conversationId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(conversation);
    }
}
