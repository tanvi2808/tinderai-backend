package org.springproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springproject.constants.LoggingConstants;
import org.springproject.dto.auth.AuthRequest;
import org.springproject.dto.auth.AuthResponse;
import org.springproject.dto.auth.VerifyAccessTokenRequest;
import org.springproject.mapper.AuthRequestMapper;
import org.springproject.dto.auth.VerifyAccessTokenResponse;
import org.springproject.service.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

   private final String classname = "AuthController";

   @Autowired
   private AuthService authService;

    @PostMapping("/sign-up")
    ResponseEntity<AuthResponse> signUp(@RequestBody AuthRequest authRequest) {
       String methodName = "AuthController:signUp";

       log.info(LoggingConstants.START_INFO_CONSTANT, methodName, authRequest);
        String accessToken = authService.signUp(AuthRequestMapper.INSTANCE.mapToSignUp(authRequest));

        log.info(LoggingConstants.END_INFO_CONSTANT, classname,methodName);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthResponse.builder()
                        .accessToken(accessToken)
                        .build());
    }

    @GetMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String methodName = "AuthController:login";

        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, authRequest);

        String accessToken = authService.login(AuthRequestMapper.INSTANCE.mapToLogin(authRequest));

        log.info(LoggingConstants.END_INFO_CONSTANT, classname,methodName);
        return  ResponseEntity.status(HttpStatus.OK)
                .body(AuthResponse.builder()
                        .accessToken(accessToken)
                        .build());
    }

    @GetMapping("/verify-token")
    ResponseEntity<VerifyAccessTokenResponse> veriftyAccessToken(@RequestBody VerifyAccessTokenRequest verifyAccessTokenRequest) {
        String methodName = "AuthController:login";

        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, verifyAccessTokenRequest);

        Long userId = authService.verifyAccessToken(verifyAccessTokenRequest);

        log.info(LoggingConstants.END_INFO_CONSTANT, classname,methodName);
        return  ResponseEntity.status(HttpStatus.OK)
                .body(VerifyAccessTokenResponse.builder()
                        .userId(userId)
                        .build());
    }
}
