package com.example.skillswap.dto;

import com.example.skillswap.enums.MatchStatus;
import com.example.skillswap.model.Match;

import java.util.UUID;

public record MatchDto(
    UUID id,
    UserDto user1,
    UserDto user2,
    MatchStatus status) {
  public static MatchDto fromMatch(Match m) {
    return new MatchDto(
        m.getId(),
        m.getUser1().toDto(),
        m.getUser2().toDto(),
        m.getStatus());
  }
}
