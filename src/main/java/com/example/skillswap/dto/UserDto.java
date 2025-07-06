
package com.example.skillswap.dto;

import java.util.UUID;

public record UserDto(
    UUID id,
    String name,
    String email,
    String photoUrl,
    double latitude,
    double longitude,
    double radiusKm) {
}
