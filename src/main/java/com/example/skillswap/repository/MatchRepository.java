package com.example.skillswap.repository;

import com.example.skillswap.model.Match;
import com.example.skillswap.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, UUID> {

    /*
     * Finds all matches that include a specific skill ID
     */
    List<Match> findBySkills_Id(Long skillId);

    /*
     * Finds all matches that include any of the given skill IDs
     */
    List<Match> findBySkills_IdIn(List<Long> skillIds);

    /*
     * Finds all matches for a user, either as user1 or user2 (initiator or
     * recipient)
     */
    List<Match> findByUser1OrUser2(User u1, User u2);

    /*
     * Finds a match between two users
     */
    @Query("SELECT m FROM Match m WHERE " +
            "(m.user1.id = :user1Id AND m.user2.id = :user2Id) OR " +
            "(m.user1.id = :user2Id AND m.user2.id = :user1Id)")
    Optional<Match> findByUsers(@Param("user1Id") UUID user1Id, @Param("user2Id") UUID user2Id);

}
