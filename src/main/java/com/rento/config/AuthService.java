package com.rento.config;

import com.rento.Constent.Content;
import com.rento.entity.Customer;
import com.rento.entity.OtpVerification;
import com.rento.repository.CustomerRepository;
import com.rento.repository.OtpRepository;
import com.rento.request.VerifyOtpRequest;
import com.rento.response.AuthResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OtpRepository otpRepository;
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;

    public String sendOtp(String mobileNumber) {
        String otp = String.valueOf(
                ThreadLocalRandom.current().nextInt(1000, 9999));
        OtpVerification otpEntity = new OtpVerification();
        otpEntity.setMobileNumber(mobileNumber);
        otpEntity.setOtp(otp);
        otpEntity.setVerified(false);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(otpEntity);
        // SMS Provider Call Here
        return "OTP Sent Successfully. OTP: " + otp; // ⚠️ dev/testing only
    }

    @Transactional
    public AuthResponse verifyOtp(
            VerifyOtpRequest request) {
        OtpVerification otp =  otpRepository
                        .findTopByMobileNumberOrderByIdDesc(
                                request.getMobileNumber())
                        .orElseThrow(
                                () -> new RuntimeException("OTP not found"));

        if (!otp.getOtp().equals(request.getOtp())) {
            throw new RuntimeException(Content.INVALID_OTP);
        }

        if (LocalDateTime.now()
                .isAfter(otp.getExpiryTime())) {
            throw new RuntimeException(Content.OTP_EXPIRED);
        }

        otp.setVerified(true);

        Customer customer =
                customerRepository
                        .findByMobileNumber(
                                request.getMobileNumber())
                        .orElseGet(() -> {

                            Customer c = new Customer();

                            c.setCustomerId(
                                    "CUST" + System.currentTimeMillis());

                            c.setMobileNumber(
                                    request.getMobileNumber());

                            c.setVerified(true);

                            return customerRepository.save(c);
                        });

        String token =jwtService.generateToken(customer.getMobileNumber());
        AuthResponse response =new AuthResponse();
        response.setCustomerId(customer.getCustomerId());
        response.setToken(token);
        response.setMessage("Login Successful");

        return response;
    }
}