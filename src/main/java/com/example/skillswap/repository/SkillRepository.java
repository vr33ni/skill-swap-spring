package com.example.skillswap.repository;

import com.example.skillswap.model.Skill;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
        Optional<Skill> findByName(String name);
}
