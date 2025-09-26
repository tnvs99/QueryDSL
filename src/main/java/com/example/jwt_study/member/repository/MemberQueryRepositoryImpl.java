package com.example.jwt_study.member.repository;

import com.example.jwt_study.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.jwt_study.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory jqf;

    @Override
    public Optional<Member> findByUsername(String username) {

        return Optional.ofNullable(
                jqf.selectFrom(member)
                        .where(member.username.eq(username))
                        .fetchOne()
        );
    }
}
