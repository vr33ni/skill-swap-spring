package com.example.skillswap.repository;

import com.example.skillswap.model.UserSkill;
import com.example.skillswap.model.UserSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkillId> {

    List<UserSkill> findByUserId(UUID userId);

    List<UserSkill> findBySkillId(Long skillId);
}
