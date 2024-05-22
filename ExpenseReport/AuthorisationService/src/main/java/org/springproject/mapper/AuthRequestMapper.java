package org.springproject.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springproject.dto.AuthRequest;
import org.springproject.model.LoginRequest;
import org.springproject.model.SignUpRequest;
import org.springproject.dto.VerifyAccessTokenRequest;

@Mapper
public interface AuthRequestMapper {

    AuthRequestMapper INSTANCE = Mappers.getMapper(AuthRequestMapper.class);

    SignUpRequest mapToSignUp(AuthRequest authRequest);
    LoginRequest  mapToLogin(AuthRequest authRequest);
}