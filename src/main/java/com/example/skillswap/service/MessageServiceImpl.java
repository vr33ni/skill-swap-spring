package com.example.skillswap.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.skillswap.model.Message;
import com.example.skillswap.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
 
    private final MessageRepository messageRepository;
    
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    
    @Override
    public Message sendMessage(UUID matchId, UUID senderId, String content) {
        return null;
        // Logic to send a message
    }
    
    @Override
    public List<Message> getMessagesForMatch(UUID matchId) {
        return null;
        // Logic to retrieve messages for a match
    }
 }
