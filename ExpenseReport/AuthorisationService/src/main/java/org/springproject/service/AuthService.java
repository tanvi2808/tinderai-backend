package org.springproject.service;

import org.springproject.model.LoginRequest;
import org.springproject.model.SignUpRequest;
import org.springproject.dto.VerifyAccessTokenRequest;

public interface AuthService {

    String signUp(SignUpRequest signUpRequest);
    String login(LoginRequest loginRequest);

    Long verifyAccessToken(VerifyAccessTokenRequest verifyAccessTokenRequest);
}
