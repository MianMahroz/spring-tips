package com.mahroz.identity.service.dto;

import com.mahroz.identity.service.config.CustomUserDetails;

import java.time.Instant;

public record RefreshTokenDto(String userName, String refreshToken, Instant expiryDate) {
}
