package org.springproject.service.auth;

import org.springproject.service.model.LoginRequest;
import org.springproject.service.model.SignUpRequest;
import org.springproject.dto.auth.VerifyAccessTokenRequest;

public interface AuthService {

    String signUp(SignUpRequest signUpRequest);
    String login(LoginRequest loginRequest);

    Long verifyAccessToken(VerifyAccessTokenRequest verifyAccessTokenRequest);
}
