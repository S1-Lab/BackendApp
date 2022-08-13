package com.s1.lab.flutter.backend.domain.relation.dto;

import com.s1.lab.flutter.backend.domain.relation.entity.Relation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RelationCreateResponseDto {

    private List<String> relationNames;

    protected RelationCreateResponseDto() {
        /* empty */
    }

    @Builder
    public RelationCreateResponseDto(final List<Relation> relations) {
        this.relationNames = relations.stream()
                .map(Relation::getName)
                .collect(Collectors.toList());
    }

    public static RelationCreateResponseDto of(final List<Relation> relations) {
        return RelationCreateResponseDto.builder()
                .relations(relations)
                .build();
    }
}
