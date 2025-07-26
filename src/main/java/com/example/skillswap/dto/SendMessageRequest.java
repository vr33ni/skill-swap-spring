package com.example.skillswap.dto;

import java.util.UUID;

public class SendMessageRequest {
  private UUID matchId;
  private UUID senderId;
  private String content;

  public SendMessageRequest(UUID matchId, UUID senderId, String content) {
    this.matchId = matchId;
    this.senderId = senderId;
    this.content = content;
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

}
