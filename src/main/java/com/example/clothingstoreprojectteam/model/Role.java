package com.example.clothingstoreprojectteam.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    private Long id;
    private String name;

    public Role(Long id) {
        this.id = id;
    }

    public Role() {

    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
