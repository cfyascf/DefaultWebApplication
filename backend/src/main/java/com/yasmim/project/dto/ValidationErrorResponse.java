package com.yasmim.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorResponse {
    private List<Violation> violations;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Violation {
        private String fieldName;
        private String message;
    }
}

