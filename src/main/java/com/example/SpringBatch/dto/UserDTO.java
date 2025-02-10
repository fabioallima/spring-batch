package com.example.SpringBatch.dto;

import java.time.Instant;

public record UserDTO(
        Long id,
        String login,
        String name,
        String location,
        String avatarUrl,
        Integer followers,
        Integer following,
        Instant createdAt,
        String reposUrl,
        String company
) {}