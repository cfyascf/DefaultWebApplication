package com.yasmim.project.services.exceptions;

import lombok.Getter;

@Getter
public class UserNotVerifiedException extends RuntimeException {

    private boolean newEmailSent;

    public UserNotVerifiedException(String message, boolean newEmailSent) {
        super(message);
        this.newEmailSent = newEmailSent;
    }
}
