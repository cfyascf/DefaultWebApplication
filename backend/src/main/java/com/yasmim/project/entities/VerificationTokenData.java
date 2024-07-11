package com.yasmim.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "VerificationToken")
public class VerificationTokenData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "token",unique = true, nullable = false)
    private String token;

    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;
}
