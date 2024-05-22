package org.springproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springproject.constants.ErrorCodeConstants;
import org.springproject.constants.LoggingConstants;
import org.springproject.data.model.AppUser;
import org.springproject.data.repo.AppUserRepository;
import org.springproject.exception.BadCredentialsException;
import org.springproject.exception.InvalidAccessTokenException;
import org.springproject.exception.UserExistsException;
import org.springproject.exception.UserNotFoundException;
import org.springproject.model.LoginRequest;
import org.springproject.model.SignUpRequest;
import org.springproject.dto.VerifyAccessTokenRequest;
import org.springproject.utils.JWTUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final AppUserRepository appUserRepository;
    private final String classname = "AuthServiceImpl";

    //@Autowired
    @Autowired
    private final  PasswordEncoder passwordEncoder;
    @Override
    public String signUp(SignUpRequest signUpRequest) {
        log.info(LoggingConstants.START_INFO_CONSTANT, classname, "signup");
        if(appUserRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            log.error(LoggingConstants.ERROR_INFO_CONSTANT, classname, signUpRequest.getEmail()+" already exists");
            throw new UserExistsException(ErrorCodeConstants.USER_EXISTS_CODE.getErrorCode()
                    , ErrorCodeConstants.USER_EXISTS_CODE.getMessage());
        }

        // Add the user to the DB
        var appUser = AppUser.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();

        //Save it

        appUserRepository.save(appUser);
        // Create Access Token

       return JWTUtils.generateAccessToken(signUpRequest.getEmail());

    }

    public String login(LoginRequest loginRequest) {
        String methodName = "AuthSErviceImpl:Login";

        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, loginRequest.getEmail());

        AppUser appUser = appUserRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
                        log.error(LoggingConstants.ERROR_INFO_CONSTANT, methodName, loginRequest.getEmail() + " does not exist");
                            return new UserNotFoundException(
                                    ErrorCodeConstants.USER_NOT_FOUND.getErrorCode(),
                                    ErrorCodeConstants.USER_NOT_FOUND.getMessage());
                        }
                );


       if(!passwordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())){
           log.error(LoggingConstants.ERROR_INFO_CONSTANT, methodName, loginRequest.getEmail() + " password is not valid");
           throw new BadCredentialsException(ErrorCodeConstants.BAD_CREDENTIALS.getErrorCode(),
                   ErrorCodeConstants.BAD_CREDENTIALS.getMessage());
       };

        var accessToken = JWTUtils.generateAccessToken(loginRequest.getEmail());
        log.info(LoggingConstants.END_INFO_CONSTANT, methodName, loginRequest.getEmail());
        return accessToken;

    }

    @Override
    public Long verifyAccessToken(VerifyAccessTokenRequest verifyAccessTokenRequest) {
        String methodName = "AuthServiceImpl:verifyAccessToken";
        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, verifyAccessTokenRequest.getAccessToken());

        String userEmail = JWTUtils.verifyAccessToken(verifyAccessTokenRequest.getAccessToken()).
                orElseThrow( () -> new InvalidAccessTokenException(ErrorCodeConstants.INVALID_ACCESS_TOKEN.getErrorCode(),
                        ErrorCodeConstants.INVALID_ACCESS_TOKEN.getMessage()));

        AppUser appUser = appUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> {
                            log.error(LoggingConstants.ERROR_INFO_CONSTANT, methodName, userEmail + " does not exist");
                            return new UserNotFoundException(
                                    ErrorCodeConstants.USER_NOT_FOUND.getErrorCode(),
                                    ErrorCodeConstants.USER_NOT_FOUND.getMessage());
                        }
                );

        log.info(LoggingConstants.END_INFO_CONSTANT, methodName, verifyAccessTokenRequest.getAccessToken());

        return appUser.getId();
    }
}
