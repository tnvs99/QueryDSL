package com.example.jwt_study.member.service;

import com.example.jwt_study.member.dto.request.CreateMemberRequestDto;
import com.example.jwt_study.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    Member findByUsername(String username);

    Member createMember(CreateMemberRequestDto requestDto);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
