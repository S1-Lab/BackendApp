package com.s1.lap.flutter.backend.domain.relation.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class RelationDeleteRequestDto {

    @NotBlank(message = "관계명을 입력해주세요.")
    private String relationName;

    @Min(value = 1L, message = "제한된 범위 밖입니다.")
    @Max(value = Long.MAX_VALUE, message = "제한된 범위 밖입니다.")
    @NotNull(message = "회원 번호를 입력해주세요.")
    private Long memberId;

    protected RelationDeleteRequestDto() {
        /* empty */
    }

    public RelationDeleteRequestDto(final String relationName, final Long memberId) {
        this.relationName = relationName;
        this.memberId = memberId;
    }
}
