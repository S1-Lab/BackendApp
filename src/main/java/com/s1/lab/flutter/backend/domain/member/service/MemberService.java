package com.s1.lab.flutter.backend.domain.member.service;

import com.s1.lab.flutter.backend.domain.member.dto.MemberCreateRequestDto;
import com.s1.lab.flutter.backend.domain.member.entity.Member;

import java.util.Optional;

public interface MemberService {
    Member save(final MemberCreateRequestDto member);
    Optional<Member> findById(final Long id);
}
