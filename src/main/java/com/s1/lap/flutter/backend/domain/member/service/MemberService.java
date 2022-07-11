package com.s1.lap.flutter.backend.domain.member.service;

import com.s1.lap.flutter.backend.domain.member.entity.Member;

public interface MemberService {
    Member findById(final Long id);
}
