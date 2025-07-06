package com.example.skillswap.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillswap.dto.SkillDto;
import com.example.skillswap.model.Skill;
import com.example.skillswap.repository.SkillRepository;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillRepository skillRepository;

    public SkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllSkills() {
        List<SkillDto> skills = skillRepository.findAll().stream()
                .map(skill -> new SkillDto(skill.getId(), skill.getName(), skill.getCategory()))
                .toList();
        return ResponseEntity.ok(skills);
    }

    // Optional (for admin use, as skills are created via UserSkillController)
    @PostMapping
    public ResponseEntity<?> createSkill(
            @RequestParam String name,
            @RequestParam(required = false) String category) {

        if (skillRepository.findByName(name).isPresent()) {
            return ResponseEntity.badRequest().body("Skill already exists");
        }

        Skill skill = new Skill();
        skill.setName(name);
        skill.setCategory(category);

        return ResponseEntity.ok(skillRepository.save(skill));
    }

}
