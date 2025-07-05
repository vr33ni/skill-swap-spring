package com.example.skillswap.service;

import com.example.skillswap.model.MatchSkill;

import java.util.List;
import java.util.UUID;

public interface MatchSkillService {
    List<MatchSkill> getSkillsForMatch(UUID matchId);
    MatchSkill addSkillToMatch(UUID matchId, Long skillId);
    void removeSkillFromMatch(UUID matchId, Long skillId);
}