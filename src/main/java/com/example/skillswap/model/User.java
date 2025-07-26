package com.example.skillswap.model;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

import com.example.skillswap.dto.UserDto;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    private UUID id;

    private String name;

    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "provider", nullable = false)
    private String provider = "local";

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(columnDefinition = "geography(Point,4326)")
    private Point location;

    @Column(name = "radius_km")
    private Double radiusKm;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private Set<UserSkill> userSkills;

    @OneToMany(mappedBy = "user1")
    private Set<Match> matchesInitiated;

    @OneToMany(mappedBy = "user2")
    private Set<Match> matchesReceived;

    @OneToMany(mappedBy = "sender")
    private Set<Message> messagesSent;

    // Getters & setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Double getRadiusKm() {
        return radiusKm;
    }

    public void setRadiusKm(Double radiusKm) {
        this.radiusKm = radiusKm;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<UserSkill> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(Set<UserSkill> userSkills) {
        this.userSkills = userSkills;
    }

    public Set<Match> getMatchesInitiated() {
        return matchesInitiated;
    }

    public void setMatchesInitiated(Set<Match> matchesInitiated) {
        this.matchesInitiated = matchesInitiated;
    }

    public Set<Match> getMatchesReceived() {
        return matchesReceived;
    }

    public void setMatchesReceived(Set<Match> matchesReceived) {
        this.matchesReceived = matchesReceived;
    }

    public Set<Message> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(Set<Message> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public UserDto toDto() {
        double lat = 0;
        double lng = 0;

        if (location != null) {
            lat = location.getY();
            lng = location.getX();
        }

        double radius = radiusKm != null ? radiusKm : 5.0;

        return new UserDto(
                id, name, email, photoUrl, lat, lng, radius);
    }

}
