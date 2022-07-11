package com.s1.lap.flutter.backend.domain.relation.service.impl;

import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.member.repository.MemberRepository;
import com.s1.lap.flutter.backend.domain.relation.dto.RelationCreateRequestDto;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import com.s1.lap.flutter.backend.domain.relation.repository.RelationRepository;
import com.s1.lap.flutter.backend.domain.relation.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RelationServiceImpl implements RelationService {

    private final RelationRepository relationRepository;
    private final MemberRepository memberRepository;


    @Autowired
    public RelationServiceImpl(final RelationRepository relationRepository, final MemberRepository memberRepository) {
        this.relationRepository = relationRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Relation save(RelationCreateRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        Relation relation = Relation.builder()
                .name(requestDto.getRelationName())
                .member(member)
                .build();
        return relationRepository.save(relation);
    }
}
