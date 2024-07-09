package com.yasmim.project.dto;

import com.yasmim.project.entities.UserData;

public record SignResponse(
        UserData user,
        String message) {
}
