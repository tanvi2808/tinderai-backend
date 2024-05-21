package org.springproject.utils;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springproject.constants.LoggingConstants;

import java.security.Key;
import java.util.Date;
import java.util.UUID;


@Slf4j
public class JWTUtils {
    public final static String ISSUER = "ET_AUTH_SERVICE";
    public static final  Key SECRET_KEY = Jwts.SIG.HS256.key().build();
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
}
