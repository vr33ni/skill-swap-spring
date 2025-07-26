package com.example.skillswap.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.skillswap.model.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, UUID>, CustomUserRepository {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE ST_DWithin(location, :point, :radius * 1000)", nativeQuery = true)
    List<User> findNearbyUsers(@Param("point") Point point, @Param("radius") double radiusKm);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET location = :newLocation WHERE id = :userId", nativeQuery = true)
    void updateLocation(@Param("userId") UUID userId, @Param("newLocation") Point newLocation);

    @Query("""
            SELECT u FROM User u
            WHERE u.id <> :userId AND u.id NOT IN (
                SELECT CASE
                    WHEN m.user1.id = :userId THEN m.user2.id
                    ELSE m.user1.id
                END
                FROM Match m
                WHERE m.user1.id = :userId OR m.user2.id = :userId
            )
            """)
    List<User> findUsersWithoutMatchWith(UUID userId);

}