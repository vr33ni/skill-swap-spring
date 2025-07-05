package com.example.skillswap.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.skillswap.enums.MatchStatus;
import com.example.skillswap.model.Match;
import com.example.skillswap.model.Skill;
import com.example.skillswap.model.User;
import com.example.skillswap.repository.MatchRepository;
import com.example.skillswap.repository.SkillRepository;
import com.example.skillswap.repository.UserRepository;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    public MatchServiceImpl(MatchRepository matchRepository,
            UserRepository userRepository,
            SkillRepository skillRepository) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public Match createMatch(UUID user1Id, UUID user2Id, List<Long> skillIds) {
        User user1 = userRepository.findById(user1Id).orElseThrow(() -> new RuntimeException("User1 not found"));
        User user2 = userRepository.findById(user2Id).orElseThrow(() -> new RuntimeException("User2 not found"));
        Set<Skill> skills = new HashSet<>(skillRepository.findAllById(skillIds));

        Match match = new Match();
        match.setId(UUID.randomUUID());
        match.setUser1(user1);
        match.setUser2(user2);
        match.setSkills(skills);
        match.setStatus(MatchStatus.PENDING);
        match.setCreatedAt(LocalDateTime.now());

        return matchRepository.save(match);
    }

    @Override
    public void updateMatchStatus(UUID matchId, String status) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Match not found"));
        match.setStatus(MatchStatus.valueOf(status));
        matchRepository.save(match);
    }

    @Override
    public List<Match> getMatchesForUser(UUID userId) {
        List<Match> matches = new ArrayList<>();
        matches.addAll(matchRepository.findByUser1Id(userId));
        matches.addAll(matchRepository.findByUser2Id(userId));
        return matches;
    }
}
