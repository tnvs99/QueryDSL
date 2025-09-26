package com.example.jwt_study.member.repository;

import com.example.jwt_study.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
