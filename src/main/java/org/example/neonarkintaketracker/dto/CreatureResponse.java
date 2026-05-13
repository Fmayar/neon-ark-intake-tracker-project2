package org.example.neonarkintaketracker.dto;

public record CreatureResponse(
        Long id,
        String name,
        String species,
        String dangerLevel,
        String condition,
        String notes,
        Long habitatId,
        String createdAt
) {
}
