package com.example.skillswap.repository;

import com.example.skillswap.model.User;
import org.locationtech.jts.geom.Point;

import java.util.List;

public interface CustomUserRepository {
    List<User> findNearbyUsers(Point point, double radiusKm);
}
