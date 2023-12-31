package com.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank @NotNull
    private String firstName;
    @NotBlank @NotNull
    private String lastName;

    @Email(message = "The email is not a valid email.")
    private String email;
    @NotBlank @NotNull
    private String gender;

    @CreationTimestamp
    private LocalDateTime createdDate;
}

