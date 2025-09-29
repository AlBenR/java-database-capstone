package com.project.back_end.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;

    @NotNull(message = "El email no puede ser nulo")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotNull(message = "La contraseña no puede ser nula")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotNull(message = "El teléfono no puede ser nulo")
    @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe tener 10 dígitos")
    private String phone;

    @NotNull(message = "La dirección no puede ser nula")
    @Size(max = 255, message = "La dirección no puede exceder 255 caracteres")
    private String address;

    public Patient() {}

    public Patient(String name, String email, String password, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}