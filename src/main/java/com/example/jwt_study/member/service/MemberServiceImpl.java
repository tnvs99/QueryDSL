package com.example.jwt_study.member.service;

import com.example.jwt_study.member.dto.request.CreateMemberRequestDto;
import com.example.jwt_study.member.entity.Member;
import com.example.jwt_study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저"));
    }

    @Override
    public Member createMember(CreateMemberRequestDto requestDto) {

        if (existsByUsername(requestDto.getUsername())) {
            throw new RuntimeException("이미 존재하는 아이디");
        }

        if (existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());

        Member member = requestDto.toEntity();
        member.setPassword(password);

        return memberRepository.save(member);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
