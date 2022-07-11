package com.s1.lap.flutter.backend.domain.relation.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class RelationCreateRequestDto {

    @NotBlank
    private String relationName;

    @Min(value = 1L, message = "제한된 범위 밖입니다.")
    @Max(value = Long.MAX_VALUE, message = "제한된 범위 밖입니다.")
    private Long memberId;

    protected RelationCreateRequestDto() {
        /* empty */
    }

    public RelationCreateRequestDto(final String relationName, final String memberId) {
        try {
            this.relationName = relationName;
            this.memberId = Long.parseLong(memberId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값을 입력할 수 없습니다.");
        } catch (Exception e) {
            throw new IllegalArgumentException("알수 없는 오류가 발생했습니다.");
        }
    }
}
