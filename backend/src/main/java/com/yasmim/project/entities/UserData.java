package com.yasmim.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "UserData")
public class UserData {

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
}
