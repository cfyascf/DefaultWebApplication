package com.yasmim.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "VerificationToken")
public class VerificationTokenData {

    public VerificationTokenData(String token, Timestamp createdAt) {
        this.token = token;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "Token",unique = true, nullable = false)
    private String token;

    @Column(name = "CreatedAt", nullable = false)
    private Timestamp createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "UserId", nullable = false)
    private UserData user;
}
