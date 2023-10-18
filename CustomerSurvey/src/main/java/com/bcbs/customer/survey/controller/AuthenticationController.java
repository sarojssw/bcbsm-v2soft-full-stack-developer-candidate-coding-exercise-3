package com.bcbs.customer.survey.controller;

import com.bcbs.customer.survey.constants.SurveyConstants;
import com.bcbs.customer.survey.dto.JwtAuthenticationResponse;
import com.bcbs.customer.survey.dto.RefreshTokenRequest;
import com.bcbs.customer.survey.dto.SignInRequest;
import com.bcbs.customer.survey.dto.SignUpRequest;
import com.bcbs.customer.survey.service.AuthenticationService;
import com.bcbs.customer.survey.utils.SurveyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest){
        try{
            return authenticationService.signup(signUpRequest);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return SurveyUtils.getResponseEntity(SurveyConstants.SIGNUP_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
