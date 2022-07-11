package com.s1.lap.flutter.backend.domain.relation.dto;

import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RelationCreateResponseDto {

    private Long relationId;
    private String relationName;

    protected RelationCreateResponseDto() {
        /* empty */
    }

    @Builder
    public RelationCreateResponseDto(final Long relationId, final String relationName) {
        this.relationId = relationId;
        this.relationName = relationName;
    }

    public static RelationCreateResponseDto of(final Relation relation) {
        return RelationCreateResponseDto.builder()
                .relationId(relation.getId())
                .relationName(relation.getName())
                .build();
    }
}
