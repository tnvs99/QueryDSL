package com.example.jwt_study.auth.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginMemberResponseDto {

    private String username;

    private String accessToken;
    private String refreshToken;
}
