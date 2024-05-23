package org.springproject.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springproject.dto.auth.AuthRequest;
import org.springproject.service.model.LoginRequest;
import org.springproject.service.model.SignUpRequest;

@Mapper
public interface AuthRequestMapper {

    AuthRequestMapper INSTANCE = Mappers.getMapper(AuthRequestMapper.class);

    SignUpRequest mapToSignUp(AuthRequest authRequest);
    LoginRequest  mapToLogin(AuthRequest authRequest);
}