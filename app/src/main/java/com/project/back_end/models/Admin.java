package com.project.back_end.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "username no puede ser nulo")
    private String username;

    @NotNull(message = "password no puede ser nulo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public Admin() {}

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}