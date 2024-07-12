package com.yasmim.project.services.impl;

import com.yasmim.project.entities.UserData;
import com.yasmim.project.entities.VerificationTokenData;
import com.yasmim.project.services.EmailService;
import com.yasmim.project.services.JWTService;
import com.yasmim.project.services.exceptions.EmailSendingException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.sql.Timestamp;
import java.util.List;

public class DefaultEmailService implements EmailService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${email.sender}")
    private String sender;

    @Value("${app.website.url}")
    private String url;

    @Override
    public VerificationTokenData createVerificationToken(UserData user) {
        VerificationTokenData token = new VerificationTokenData();
        token.setToken(jwtService.generateVerificationToken(user.getEmail()));
        token.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        token.setUser(user);

        return token;
    }

    @Override
    public void sendVerificationEmail(VerificationTokenData verificationToken) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            helper.setFrom(sender);
            helper.setTo(verificationToken.getUser().getEmail());
            helper.setSubject("Verify your email to activate your account.");
            helper.setText("Please follow the link bellow to verify your email and activate your account:\n" +
                    url + "auth/verify?token=" + verificationToken.getToken());

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new EmailSendingException("Failed to send email to user.");
        }
    }
}
