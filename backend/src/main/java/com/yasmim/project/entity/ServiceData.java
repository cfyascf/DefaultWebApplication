package com.yasmim.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ServiceData")
public class ServiceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Manager")
    private UserData manager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserData getManager() {
        return manager;
    }

    public void setManager(UserData manager) {
        this.manager = manager;
    }
}
