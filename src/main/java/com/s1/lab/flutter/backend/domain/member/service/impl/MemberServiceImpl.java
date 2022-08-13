package com.s1.lab.flutter.backend.domain.member.service.impl;

import com.s1.lab.flutter.backend.domain.member.dto.MemberCreateRequestDto;
import com.s1.lab.flutter.backend.domain.member.entity.Member;
import com.s1.lab.flutter.backend.domain.member.repository.MemberRepository;
import com.s1.lab.flutter.backend.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Member save(final MemberCreateRequestDto requestDto) {
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 회원 입니다.");
        }
        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .phoneNumber(requestDto.getPhoneNumber())
                .profileImageURL(requestDto.getProfileImageURL())
                .build();
        return memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findById(final Long id) {
        return memberRepository.findById(id);
    }
}
