package com.example.skillswap.controller;

import com.example.skillswap.model.MatchSkill;
import com.example.skillswap.service.MatchSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/matches/{matchId}/skills")
public class MatchSkillController {

    private final MatchSkillService matchSkillService;

    public MatchSkillController(MatchSkillService matchSkillService) {
        this.matchSkillService = matchSkillService;
    }

    @GetMapping
    public ResponseEntity<List<MatchSkill>> getSkillsForMatch(@PathVariable UUID matchId) {
        return ResponseEntity.ok(matchSkillService.getSkillsForMatch(matchId));
    }

    @PostMapping
    public ResponseEntity<MatchSkill> addSkillToMatch(@RequestParam UUID matchId, @RequestParam Long skillId) {
        return ResponseEntity.ok(matchSkillService.addSkillToMatch(matchId, skillId));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeSkillFromMatch(@RequestParam UUID matchId, @RequestParam Long skillId) {
        matchSkillService.removeSkillFromMatch(matchId, skillId);
        return ResponseEntity.ok().build();
    }
}