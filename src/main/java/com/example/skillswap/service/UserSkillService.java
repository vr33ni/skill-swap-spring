package com.example.skillswap.service;

import com.example.skillswap.model.UserSkill;

import java.util.List;
import java.util.UUID;

public interface UserSkillService {
    UserSkill addSkillToUser(UUID userId, Long skillId, String type);
    void removeSkillFromUser(UUID userId, Long skillId);
    List<UserSkill> getSkillsForUser(UUID userId);
}
