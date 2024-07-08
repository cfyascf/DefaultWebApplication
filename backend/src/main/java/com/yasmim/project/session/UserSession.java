package com.yasmim.project.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserSession {
    private String username;
    private Integer role;
}
