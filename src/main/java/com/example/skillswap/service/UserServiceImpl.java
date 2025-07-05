package com.example.skillswap.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.skillswap.model.Skill;
import com.example.skillswap.model.User;
import com.example.skillswap.repository.SkillRepository;
import com.example.skillswap.repository.UserRepository;
import com.example.skillswap.repository.UserSkillRepository;
import org.locationtech.jts.geom.Point;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;

    public UserServiceImpl(UserRepository userRepository,
            SkillRepository skillRepository,
            UserSkillRepository userSkillRepository) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findNearbyUsers(Point point, double radiusKm) {
        return userRepository.findNearbyUsers(point, radiusKm);
    }

    @Override
    public void updateLocation(UUID userId, Point newLocation) {
        userRepository.updateLocation(userId, newLocation);
    }

}
