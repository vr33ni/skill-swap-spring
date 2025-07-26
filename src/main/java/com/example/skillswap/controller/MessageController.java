package com.example.skillswap.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillswap.dto.MessageDto;
import com.example.skillswap.dto.SendMessageRequest;
import com.example.skillswap.model.Match;
import com.example.skillswap.model.Message;
import com.example.skillswap.repository.MatchRepository;
import com.example.skillswap.repository.MessageRepository;
import com.example.skillswap.repository.UserRepository;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  @Autowired
  MessageRepository messageRepository;
  @Autowired
  MatchRepository matchRepository;
  @Autowired
  UserRepository userRepository;

  @PostMapping
  public ResponseEntity<?> sendMessage(@RequestBody SendMessageRequest request) {
    Match match = matchRepository.findById(request.getMatchId()).orElseThrow();

    // Optional: block if REJECTED
    if ("REJECTED".equals(match.getStatus())) {
      return ResponseEntity.badRequest().body("Match was rejected.");
    }

    Message msg = new Message();
    msg.setMatch(match);
    msg.setSender(userRepository.findById(request.getSenderId()).orElseThrow());
    msg.setContent(request.getContent());
    msg.setSentAt(LocalDateTime.now());
    messageRepository.save(msg);

    return ResponseEntity.ok(msg);
  }

  @GetMapping("/{matchId}")
  public ResponseEntity<?> getMessagesForMatch(@PathVariable UUID matchId) {
    Match match = matchRepository.findById(matchId).orElseThrow();
    List<Message> messages = messageRepository.findByMatchOrderBySentAtAsc(match);

    List<MessageDto> dtos = messages.stream()
        .map(MessageDto::new)
        .collect(Collectors.toList());

    return ResponseEntity.ok(dtos);
  }

}
