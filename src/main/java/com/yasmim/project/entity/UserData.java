package com.yasmim.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UserData")
public class UserData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Username")
    private String username;

    @Column(name = "FullName")
    private String fullname;

    @Column(name = "Email")
    private String email;

    @Column(name = "Role")
    private Integer role;

    @OneToOne
    @JoinColumn(name = "Department")
    private DepartmentData department;

    @Column(name = "Password")
    private String password;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public DepartmentData getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentData department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
