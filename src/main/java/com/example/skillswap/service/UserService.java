package com.example.skillswap.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.locationtech.jts.geom.Point;

import com.example.skillswap.model.User;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(UUID id);
    Optional<User> getUserByEmail(String email);
    void updateLocation(UUID userId, Point newLocation);
     List<User> findNearbyUsers(Point point, double radiusKm);
}
