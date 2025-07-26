package com.example.skillswap.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.skillswap.model.Message;

public class MessageDto {
  private UUID id;
  private UUID matchId;
  private UUID senderId;
  private String content;
  private LocalDateTime sentAt;

  public MessageDto(Message m) {
    this.id = m.getId();
    this.matchId = m.getMatch().getId();
    this.senderId = m.getSender().getId();
    this.content = m.getContent();
    this.sentAt = m.getSentAt();
  }

  // Getters and setters (or use Lombok @Data for brevity)
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getMatchId() {
    return matchId;
  }

  public void setMatchId(UUID matchId) {
    this.matchId = matchId;
  }

  public UUID getSenderId() {
    return senderId;
  }

  public void setSenderId(UUID senderId) {
    this.senderId = senderId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getSentAt() {
    return sentAt;
  }

  public void setSentAt(LocalDateTime sentAt) {
    this.sentAt = sentAt;
  }
}
