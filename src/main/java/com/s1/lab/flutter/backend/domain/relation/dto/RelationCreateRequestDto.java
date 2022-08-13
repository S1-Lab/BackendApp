package com.s1.lab.flutter.backend.domain.relation.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class RelationCreateRequestDto {

    @NotBlank(message = "관계명을 입력해주세요.")
    private String relationName;

    @Min(value = 1L, message = "제한된 범위 밖입니다.")
    @Max(value = Long.MAX_VALUE, message = "제한된 범위 밖입니다.")
    @NotNull(message = "회원 번호를 입력해주세요.")
    private Long memberId;

    protected RelationCreateRequestDto() {
        /* empty */
    }

    @Builder
    public RelationCreateRequestDto(final String relationName, final Long memberId) {
        this.relationName = relationName;
        this.memberId = memberId;
    }
}
