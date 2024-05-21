package org.springproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springproject.constants.ErrorCodeConstants;
import org.springproject.constants.LoggingConstants;
import org.springproject.data.model.AppUser;
import org.springproject.data.repo.AppUserRepository;
import org.springproject.exception.UserExistsException;
import org.springproject.model.SignUpRequest;
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
}
