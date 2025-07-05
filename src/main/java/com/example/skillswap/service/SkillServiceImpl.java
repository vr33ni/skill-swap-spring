package com.example.skillswap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.skillswap.model.Skill;
import com.example.skillswap.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill createSkill(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Optional<Skill> getSkillByName(String name) {
        return skillRepository.findByName(name);
    }
}

