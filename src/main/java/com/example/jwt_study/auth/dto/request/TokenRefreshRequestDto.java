package com.example.jwt_study.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TokenRefreshRequestDto {

    @NotBlank(message = "토큰을 입력해주세요")
    private String refreshToken;
}
