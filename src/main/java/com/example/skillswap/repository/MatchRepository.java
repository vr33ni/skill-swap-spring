package com.example.skillswap.repository;

import com.example.skillswap.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, UUID> {

    List<Match> findByUser1Id(UUID userId);

    List<Match> findByUser2Id(UUID userId);

    List<Match> findBySkills_Id(Long skillId);

    List<Match> findBySkills_IdIn(List<Long> skillIds);
}
