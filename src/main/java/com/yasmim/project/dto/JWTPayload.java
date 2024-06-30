package com.yasmim.project.dto;

public record JWTPayload(
        String username,
        Integer role) {
}
