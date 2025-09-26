package com.example.jwt_study.member.repository;

import com.example.jwt_study.member.entity.Member;

import java.util.Optional;

public interface MemberQueryRepository {

    Optional<Member> findByUsername(String username);
}
