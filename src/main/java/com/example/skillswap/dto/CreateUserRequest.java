package com.example.skillswap.dto;

import java.util.List;

public class CreateUserRequest {
  private String name;
  private String email;
  private double latitude;
  private double longitude;
  private String password; // nullable for OAuth
  private String provider; // "local" | "google" | "apple"
  private String providerId;
  private List<String> skillsOffered;
  private List<String> skillsNeeded;

  public CreateUserRequest() {
  }

  public CreateUserRequest(String name, String email, double latitude, double longitude) {
    this.name = name;
    this.email = email;
    this.latitude = latitude;
    this.longitude = longitude;
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

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public List<String> getSkillsOffered() {
    return skillsOffered;
  }

  public void setSkillsOffered(List<String> skillsOffered) {
    this.skillsOffered = skillsOffered;
  }

  public List<String> getSkillsNeeded() {
    return skillsNeeded;
  }

  public void setSkillsNeeded(List<String> skillsNeeded) {
    this.skillsNeeded = skillsNeeded;
  }
}
