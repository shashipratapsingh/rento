package com.rento.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String customerId;

    @Column(unique = true)
    private String mobileNumber;

    private Boolean verified;
    private String role;
    // getters setters
}