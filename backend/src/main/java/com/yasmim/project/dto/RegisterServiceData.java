package com.yasmim.project.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterServiceData {

    @NotBlank
    @Size(min = 4)
    private String name;

    @NotBlank
    @Size(min = 10)
    private String description;

    @NotBlank
    private String managerName;
}
