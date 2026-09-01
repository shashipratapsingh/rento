package com.rento.request;

import lombok.Data;

@Data
public class SendOtpRequest {

    private String mobileNumber;
    private String otp;

    // getter setter
}