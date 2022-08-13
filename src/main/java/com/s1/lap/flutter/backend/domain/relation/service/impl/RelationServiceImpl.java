package com.s1.lap.flutter.backend.domain.relation.service.impl;

import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.member.repository.MemberRepository;
import com.s1.lap.flutter.backend.domain.relation.dto.RelationCreateRequestDto;
import com.s1.lap.flutter.backend.domain.relation.dto.RelationDeleteRequestDto;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import com.s1.lap.flutter.backend.domain.relation.repository.RelationRepository;
import com.s1.lap.flutter.backend.domain.relation.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Relation> saveAndFindAll(final RelationCreateRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        if (relationRepository.existsByNameAndMemberId(requestDto.getRelationName(), requestDto.getMemberId())) {
            throw new IllegalArgumentException("이미 존재하는 관계 입니다.");
        }
        Relation relation = relationRepository.save(
                Relation.builder()
                        .name(requestDto.getRelationName())
                        .member(member)
                        .build());
        member.addRelation(relation);
        return relationRepository.findByMemberId(member.getId());
    }

    @Override
    @Transactional
    public List<Relation> deleteAndFindAll(final RelationDeleteRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));
        Relation relation = relationRepository.findByNameAndMemberId(requestDto.getRelationName(), requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관계 입니다."));
        member.removeRelation(relation);
        return relationRepository.findByMemberId(member.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Relation> findByMemberId(final Long memberId) {
        return relationRepository.findByMemberId(memberId);
    }
}
