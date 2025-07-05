package com.example.skillswap.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillswap.model.UserSkill;
import com.example.skillswap.service.UserSkillService;

@RestController
@RequestMapping("/api/users/{userId}/skills")
public class UserSkillController {

    private final UserSkillService userSkillService;

    public UserSkillController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @PostMapping
    public ResponseEntity<UserSkill> addSkillToUser(
            @PathVariable UUID userId,
            @RequestParam Long skillId,
            @RequestParam String type
    ) {
        UserSkill userSkill = userSkillService.addSkillToUser(userId, skillId, type);
        return ResponseEntity.ok(userSkill);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> removeSkillFromUser(
            @PathVariable UUID userId,
            @PathVariable Long skillId
     ) {
        userSkillService.removeSkillFromUser(userId, skillId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserSkill>> getSkillsForUser(
            @PathVariable UUID userId
    ) {
        List<UserSkill> userSkills = userSkillService.getSkillsForUser(userId);
        return ResponseEntity.ok(userSkills);
    }
}