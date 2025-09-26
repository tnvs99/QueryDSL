package com.example.jwt_study.member.dto.request;

import com.example.jwt_study.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMemberRequestDto {

    @NotBlank(message = "아이디는 필수입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .email(email)
                .build();
    }
}
