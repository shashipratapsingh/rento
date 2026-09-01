package com.rento.request;

import lombok.Data;

@Data
public class VerifyOtpRequest {

    private String mobileNumber;
    private String otp;

    // getter setter
}