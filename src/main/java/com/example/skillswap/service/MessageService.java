package com.example.skillswap.service;

import java.util.List;
import java.util.UUID;

import com.example.skillswap.model.Message;

public interface MessageService {
    Message sendMessage(UUID matchId, UUID senderId, String content);
    List<Message> getMessagesForMatch(UUID matchId);
}
