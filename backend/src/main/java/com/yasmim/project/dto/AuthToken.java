package com.yasmim.project.dto;

public record AuthToken(
        String token,
        Long id,
        String message) {
}
