package com.yasmim.project.services;

import com.yasmim.project.entities.UserData;
import com.yasmim.project.entities.VerificationTokenData;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailService {
    public void sendVerificationEmail(VerificationTokenData verificationToken);

//    CHECK SEALED INTERFACES TO IMPLEMENT EXCEPTIONS
}
