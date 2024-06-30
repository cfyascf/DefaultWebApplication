package com.yasmim.project.dto;

import com.yasmim.project.entity.UserData;

public record SignResponse(
        UserData user,
        String message) {
}
