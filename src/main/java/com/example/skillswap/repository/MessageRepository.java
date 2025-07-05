package com.example.skillswap.repository;

import com.example.skillswap.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findByMatchId(UUID matchId);

    List<Message> findBySenderId(UUID senderId);
}
