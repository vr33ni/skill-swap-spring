package com.example.skillswap.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillswap.dto.CreateUserRequest;
import com.example.skillswap.dto.LoginRequest;
import com.example.skillswap.dto.UserDto;
import com.example.skillswap.model.Match;
import com.example.skillswap.model.Skill;
import com.example.skillswap.model.User;
import com.example.skillswap.model.UserSkill;
import com.example.skillswap.repository.MatchRepository;
import com.example.skillswap.repository.SkillRepository;
import com.example.skillswap.repository.UserRepository;
import com.example.skillswap.repository.UserSkillRepository;

import jakarta.transaction.Transactional;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final MatchRepository matchRepository;
    private final UserSkillRepository userSkillRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
            SkillRepository skillRepository, MatchRepository matchRepository,
            UserSkillRepository userSkillRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
        this.matchRepository = matchRepository;
    }

    // List all users
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(User::toDto)
                .toList();
    }

    // Create a new user
    @PostMapping
    @Transactional
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setProvider("local");
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        Point point = geometryFactory.createPoint(new Coordinate(request.getLongitude(), request.getLatitude()));
        user.setLocation(point);

        userRepository.save(user);

        for (String skillName : request.getSkillsOffered()) {
            Skill skill = skillRepository.findByName(skillName)
                    .orElseGet(() -> {
                        Skill s = new Skill();
                        s.setName(skillName);
                        return skillRepository.save(s);
                    });

            UserSkill us = new UserSkill();
            us.setUser(user);
            us.setSkill(skill);
            us.setType("OFFER");
            userSkillRepository.save(us);
        }

        for (String skillName : request.getSkillsNeeded()) {
            Skill skill = skillRepository.findByName(skillName)
                    .orElseGet(() -> {
                        Skill s = new Skill();
                        s.setName(skillName);
                        return skillRepository.save(s);
                    });

            UserSkill us = new UserSkill();
            us.setUser(user);
            us.setSkill(skill);
            us.setType("NEED");
            userSkillRepository.save(us);
        }

        return ResponseEntity.ok(user.toDto());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        return ResponseEntity.ok(user.toDto());
    }

    @GetMapping("/available")
    public ResponseEntity<List<UserDto>> getAvailableUsers(@RequestParam UUID userId) {
        List<User> availableUsers = userRepository.findUsersWithoutMatchWith(userId);
        return ResponseEntity.ok(availableUsers.stream().map(User::toDto).toList());
    }

}
