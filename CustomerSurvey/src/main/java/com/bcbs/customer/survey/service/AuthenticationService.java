package com.bcbs.customer.survey.service;

import com.bcbs.customer.survey.dto.JwtAuthenticationResponse;
import com.bcbs.customer.survey.dto.RefreshTokenRequest;
import com.bcbs.customer.survey.dto.SignInRequest;
import com.bcbs.customer.survey.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    public ResponseEntity<String> signup(SignUpRequest signUpRequest);

    public JwtAuthenticationResponse signin(SignInRequest signInRequest);

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
