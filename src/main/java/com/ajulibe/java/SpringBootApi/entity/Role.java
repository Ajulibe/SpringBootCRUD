package com.ajulibe.java.SpringBootApi.entity;

import com.ajulibe.java.SpringBootApi.config.permissions.ERole;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole role;

    public Role() {
    }

    public Role(ERole name) {
        this.role = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return role;
    }

    public void setName(ERole name) {
        this.role = name;
    }
}