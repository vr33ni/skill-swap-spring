package com.example.skillswap.service;

import java.util.List;
import java.util.Optional;

import com.example.skillswap.model.Skill;

public interface SkillService {
    Skill createSkill(String name);
    List<Skill> getAllSkills();
    Optional<Skill> getSkillByName(String name);
}
