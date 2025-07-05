package com.example.skillswap.repository;

import com.example.skillswap.model.MatchSkill;
import com.example.skillswap.model.MatchSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MatchSkillRepository extends JpaRepository<MatchSkill, MatchSkillId> {
    List<MatchSkill> findByMatchId(UUID matchId);
}