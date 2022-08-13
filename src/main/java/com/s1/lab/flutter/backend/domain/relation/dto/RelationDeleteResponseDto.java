package com.s1.lab.flutter.backend.domain.relation.dto;

import com.s1.lab.flutter.backend.domain.relation.entity.Relation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RelationDeleteResponseDto {

    private List<String> relationNames;

    protected RelationDeleteResponseDto() {
        /* empty */
    }

    @Builder
    public RelationDeleteResponseDto(final List<Relation> relations) {
        this.relationNames = relations.stream()
                .map(Relation::getName)
                .collect(Collectors.toList());
    }

    public static RelationDeleteResponseDto of(final List<Relation> relations) {
        return RelationDeleteResponseDto.builder()
                .relations(relations)
                .build();
    }
}
