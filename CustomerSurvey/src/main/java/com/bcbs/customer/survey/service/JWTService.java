package com.bcbs.customer.survey.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    public String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);
}
