package com.example.skillswap.service;

import java.util.List;
import java.util.UUID;

import com.example.skillswap.model.Match;

public interface MatchService {
    Match createMatch(UUID user1Id, UUID user2Id, List<Long> skillIds);

    void updateMatchStatus(UUID matchId, String status);
}
