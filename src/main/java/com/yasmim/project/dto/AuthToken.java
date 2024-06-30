package com.yasmim.project.dto;

public record AuthToken(
        String token,
        Boolean success,
        String message) {
}
