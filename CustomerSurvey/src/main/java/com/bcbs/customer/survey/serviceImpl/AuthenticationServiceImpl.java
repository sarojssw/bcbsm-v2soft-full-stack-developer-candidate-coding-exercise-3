package com.bcbs.customer.survey.serviceImpl;

import com.bcbs.customer.survey.constants.SurveyConstants;
import com.bcbs.customer.survey.repository.UserRepository;
import com.bcbs.customer.survey.dto.JwtAuthenticationResponse;
import com.bcbs.customer.survey.dto.RefreshTokenRequest;
import com.bcbs.customer.survey.dto.SignInRequest;
import com.bcbs.customer.survey.dto.SignUpRequest;
import com.bcbs.customer.survey.entity.User;
import com.bcbs.customer.survey.service.AuthenticationService;
import com.bcbs.customer.survey.service.JWTService;
import com.bcbs.customer.survey.utils.SurveyUtils;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public ResponseEntity<String> signup(SignUpRequest signUpRequest){

        try{
            if(validateSignUpRequest(signUpRequest)){
                User user = userRepository.findByUserName(signUpRequest.getUsername());
                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromMap(signUpRequest));
                    return SurveyUtils.getResponseEntity("User successfully registered.", HttpStatus.OK);
                } else {
                    return SurveyUtils.getResponseEntity("User name already exists.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return SurveyUtils.getResponseEntity(SurveyConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return SurveyUtils.getResponseEntity(SurveyConstants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpRequest(SignUpRequest signUpRequest){
        if(signUpRequest==null || StringUtils.isEmpty(signUpRequest.getName()) || StringUtils.isEmpty(signUpRequest.getUsername()) || StringUtils.isEmpty(signUpRequest.getPassword())){
            return false;
        }
        return true;
    }

    private User getUserFromMap(SignUpRequest signUpRequest){
        User user = new User();

        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setRole(SurveyConstants.USER_ROLE);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return user;

    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        User user = null;
        try {
            user = userRepository.findByUserName(signInRequest.getUsername());
        } catch(Exception ex){
            throw new IllegalArgumentException(SurveyConstants.INVALID_LOGIN);
        }

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwtToken);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userName = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = null;
        try{
            user = userRepository.findByUserName(userName);
        } catch(Exception ex){
            throw new IllegalArgumentException(SurveyConstants.INVALID_TOKEN);
        }

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwtToken = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwtToken);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
