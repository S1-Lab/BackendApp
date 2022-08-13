package com.s1.lap.flutter.backend.domain.relation.service;

import com.s1.lap.flutter.backend.domain.relation.dto.RelationCreateRequestDto;
import com.s1.lap.flutter.backend.domain.relation.dto.RelationDeleteRequestDto;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;

import java.util.List;

public interface RelationService {
    List<Relation> saveAndFindAll(final RelationCreateRequestDto requestDto);
    List<Relation> deleteAndFindAll(final RelationDeleteRequestDto requestDto);
    List<Relation> findByMemberId(final Long memberId);
}
