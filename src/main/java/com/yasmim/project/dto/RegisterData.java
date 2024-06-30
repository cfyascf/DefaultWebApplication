package com.yasmim.project.dto;

public record RegisterData(
        String username,
        String fullname,
        String email,
        Integer role,
        String department,
        String password,
        String confirmPassword) {
}
