package com.s1.lap.flutter.backend.domain.member.service.impl;

import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.member.repository.MemberRepository;
import com.s1.lap.flutter.backend.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Member findById(final Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 입니다."));
    }
}
