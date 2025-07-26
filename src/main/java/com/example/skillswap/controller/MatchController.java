package com.example.skillswap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillswap.dto.MatchDto;
import com.example.skillswap.enums.MatchStatus;
import com.example.skillswap.model.Match;
import com.example.skillswap.model.User;
import com.example.skillswap.repository.MatchRepository;
import com.example.skillswap.repository.SkillRepository;
import com.example.skillswap.repository.UserRepository;
import com.example.skillswap.service.MatchService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final MatchService matchService;

    public MatchController(MatchRepository matchRepository, UserRepository userRepository,
            MatchService matchService) {
        this.matchService = matchService;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> createMatch(
            @RequestParam UUID user1Id,
            @RequestParam UUID user2Id,
            @RequestParam(required = false) List<Long> skillIds) {
        Match match = matchService.createMatch(user1Id, user2Id, skillIds != null ? skillIds : List.of());
        return ResponseEntity.ok(match);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateMatchStatus(
            @PathVariable UUID id,
            @RequestParam MatchStatus status) {
        Match match = matchRepository.findById(id).orElseThrow();
        match.setStatus(status);
        matchRepository.save(match);
        return ResponseEntity.ok(match);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getMatchesForUser(@PathVariable UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Match> matches = matchRepository.findByUser1OrUser2(user, user);

        List<MatchDto> dtoList = matches.stream()
                .map(MatchDto::fromMatch)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<?> getMatchesByStatus(
            @PathVariable UUID userId,
            @PathVariable MatchStatus status) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Match> matches = matchRepository.findByUser1OrUser2(user, user)
                .stream()
                .filter(m -> m.getStatus() == status)
                .toList();

        List<MatchDto> dtoList = matches.stream()
                .map(MatchDto::fromMatch)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping
    public ResponseEntity<List<MatchDto>> getAllMatches() {
        List<Match> matches = matchRepository.findAll();

        List<MatchDto> dtoList = matches.stream()
                .map(MatchDto::fromMatch)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

}
