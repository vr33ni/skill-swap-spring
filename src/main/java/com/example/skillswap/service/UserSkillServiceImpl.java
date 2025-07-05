package com.example.skillswap.service;

import com.example.skillswap.model.*;
import com.example.skillswap.repository.SkillRepository;
import com.example.skillswap.repository.UserRepository;
import com.example.skillswap.repository.UserSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserSkillServiceImpl implements UserSkillService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;

    public UserSkillServiceImpl(UserRepository userRepository,
                                SkillRepository skillRepository,
                                UserSkillRepository userSkillRepository) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
    }

   @Override
public UserSkill addSkillToUser(UUID userId, Long skillId, String type) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Skill skill = skillRepository.findById(skillId)
            .orElseThrow(() -> new RuntimeException("Skill not found"));

    UserSkillId id = new UserSkillId(userId, skillId);

    if (userSkillRepository.existsById(id)) {
        throw new RuntimeException("User already has this skill");
    }

    UserSkill userSkill = new UserSkill();
    userSkill.setUser(user);
    userSkill.setSkill(skill);
    userSkill.setType(type.toUpperCase());

    return userSkillRepository.save(userSkill);
}

    @Override
    public void removeSkillFromUser(UUID userId, Long skillId) {
        UserSkillId userSkillId = new UserSkillId(userId, skillId);

        if (!userSkillRepository.existsById(userSkillId)) {
            throw new RuntimeException("No such user-skill relation");
        }

        userSkillRepository.deleteById(userSkillId);
    }

    @Override
    public List<UserSkill> getSkillsForUser(UUID userId) {
        return userSkillRepository.findAll().stream()
                .filter(us -> us.getUser().getId().equals(userId))
                .toList();
    }
}
