package com.example.main_service.service;

import com.example.data.dto.request.RefreshTokenRequest;
import com.example.data.dto.response.NewTokenResponse;
import com.example.main_service.exception.TokenHasExpiredException;
import com.example.main_service.security.CookUserDetailsService;
import com.example.main_service.security.JwtUtils;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {
    private final JwtUtils jwtUtils;

    private final CookUserDetailsService cookUserDetailsService;

    public RefreshTokenService(CookUserDetailsService cookUserDetailsService, JwtUtils jwtUtils) {
        this.cookUserDetailsService = cookUserDetailsService;
        this.jwtUtils = jwtUtils;
    }

    public NewTokenResponse createNewToken(RefreshTokenRequest refreshTokenRequest) {
        if (!jwtUtils.validateJwtToken(refreshTokenRequest.getRefreshToken())) {
            throw new TokenHasExpiredException(refreshTokenRequest.getRefreshToken(), "Токен истек");
        }
        String login = jwtUtils.getLoginFromJwtToken(refreshTokenRequest.getRefreshToken());

        String accessToken = jwtUtils.generateJWTToken(login,
                jwtUtils.getAuthoritiesFromToken(refreshTokenRequest.getRefreshToken()));
        String refreshToken = jwtUtils.generateRefreshToken(
                login, jwtUtils.getAuthoritiesFromToken(refreshTokenRequest.getRefreshToken()));
        return new NewTokenResponse(accessToken, refreshToken);
    }
}
