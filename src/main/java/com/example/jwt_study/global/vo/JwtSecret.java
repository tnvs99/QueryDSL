package com.example.jwt_study.global.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtSecret {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access}")
    private Long accessToken;

    @Value("${jwt.refresh}")
    private Long refreshToken;
}
