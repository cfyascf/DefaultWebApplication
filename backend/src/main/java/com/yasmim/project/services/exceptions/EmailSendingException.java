package com.yasmim.project.services.exceptions;

public class EmailSendingException extends RuntimeException {

    public EmailSendingException(String message) {
        super(message);
    }
}
