package com.example.jwt_study.auth.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NotBlank
@Builder
@Setter
@Getter
@NoArgsConstructor
public class TokenRefreshResponseDto {

    private String accessToken;
    private String refreshToken;
}
