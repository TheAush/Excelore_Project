package com.xcelore.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String name;

    @NotBlank
    private String city;

    @Email
    private String email;

    @NotBlank
    @Size(min = 10)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    // Getters and Setters
}
