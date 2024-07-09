package com.yasmim.project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterData {

    @Size(min = 3)
    @NotBlank
    private String username;

    @Size(min = 5)
    @NotBlank
    private String fullname;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Integer role;

    @NotBlank
    private String department;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;

    @NotBlank
    private String confirmPassword;
}
