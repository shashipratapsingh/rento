package com.rento.repository;

import com.rento.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository
        extends JpaRepository<OtpVerification, Long> {

    Optional<OtpVerification>
    findTopByMobileNumberOrderByIdDesc(String mobileNumber);
    Optional<OtpVerification> findByMobileNumber(String mobileNumber);
}