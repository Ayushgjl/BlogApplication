package com.example.blog.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Role {
    @Id
    @GeneratedValue
    private Long id;
    String name;

 Role() {}

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
