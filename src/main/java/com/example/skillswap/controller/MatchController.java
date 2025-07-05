package com.example.skillswap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillswap.enums.MatchStatus;
import com.example.skillswap.model.Match;
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
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }   

@PostMapping
public ResponseEntity<Match> createMatch(@RequestParam UUID user1Id,
                                         @RequestParam UUID user2Id,
                                         @RequestParam List<Long> skillIds) {
    return ResponseEntity.ok(matchService.createMatch(user1Id, user2Id, skillIds));
}

    @PatchMapping("/{matchId}/status")
    public ResponseEntity<Void> updateMatchStatus(@PathVariable UUID matchId,
                                                  @RequestParam MatchStatus status) {
        matchService.updateMatchStatus(matchId, status.name());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Match>> getMatchesForUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(matchService.getMatchesForUser(userId));
    }
    
}
