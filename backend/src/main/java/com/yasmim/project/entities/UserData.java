package com.yasmim.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "UserData")
public class UserData {

    public UserData(String username, String fullname, String email, Integer role,
                    String password) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
        this.password = password;
        this.tokens = new ArrayList<>();
        this.emailVerified = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Username")
    private String username;

    @Column(name = "Fullname")
    private String fullname;

    @Column(name = "Email")
    private String email;

    @Column(name = "Role")
    private Integer role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Department")
    private DepartmentData department;

    @Column(name = "Password")
    private String password;

    @OneToMany(mappedBy = "user")
    @OrderBy("id desc")
    private List<VerificationTokenData> tokens;

    @Column(name = "emailVerified", nullable = false)
    private Boolean emailVerified;
}
