package com.rento.response;

import lombok.Data;

@Data
public class AuthResponse {

    private String customerId;
    private String token;
    private String message;

    // getter setter
}