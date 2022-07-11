package com.s1.lap.flutter.backend.domain.relation.service;

import com.s1.lap.flutter.backend.domain.relation.dto.RelationCreateRequestDto;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;

public interface RelationService {

    Relation save(final RelationCreateRequestDto requestDto);
}
