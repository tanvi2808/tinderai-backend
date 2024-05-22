package org.springproject.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springproject.constants.LoggingConstants;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;



@Slf4j
public class JWTUtils {
    public final static String ISSUER = "ET_AUTH_SERVICE";
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static String generateAccessToken(String email){

        String methodName="JWTUtils:generateAccessToken";
        log.info(LoggingConstants.START_INFO_CONSTANT,methodName,email);

        final Date issuedAt = new Date();
        var expirationDate = DateUtils.addMinutes(issuedAt,15);


        var  accessToken = Jwts.builder()
                .issuer(ISSUER)
                .id(UUID.randomUUID().toString())
                .subject(email)
                .issuedAt(issuedAt)
                .expiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();

        log.info(LoggingConstants.END_INFO_CONSTANT,methodName,email);

        return accessToken;





    }

    public static Optional<String> verifyAccessToken(String accessToken) {
        String methodName="JWTUtils:verifyAccessToken";
        log.info(LoggingConstants.START_INFO_CONSTANT,methodName,accessToken);
        var username =  parseToken(accessToken).map(Claims::getSubject);
        log.info(LoggingConstants.END_INFO_CONSTANT,methodName,accessToken);
        return username;
    }

    public static Optional<Claims>  parseToken(String accessToken){
        String methodName = "JWTUtils:parseToken";
        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, accessToken);


        JwtParser jwtParser= Jwts.parser().verifyWith(SECRET_KEY).build();
        log.info("Secret-Key:"+ SECRET_KEY);

        Optional<Claims> optionalClaims;
       try{
            optionalClaims=  Optional.of(jwtParser.parseSignedClaims(accessToken).getPayload());
       } catch (JwtException | IllegalArgumentException e){

           log.error(LoggingConstants.ERROR_INFO_CONSTANT, methodName, e.getMessage());
           return Optional.empty();
       }
        log.info(LoggingConstants.END_INFO_CONSTANT, methodName, accessToken);
        return optionalClaims;
    }
}
