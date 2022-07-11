package com.s1.lap.flutter.backend.domain.post.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
public class PostReadRequestDto {

    @Min(value = 1L, message = "제한된 범위 밖입니다.")
    @Max(value = Long.MAX_VALUE, message = "제한된 범위 밖입니다.")
    private Long memberId;

    protected PostReadRequestDto() {
        /* empty */
    }

    public PostReadRequestDto(final String memberId) {
        try {
            this.memberId = Long.parseLong(memberId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값을 입력할 수 없습니다.");
        } catch (Exception e) {
            throw new IllegalArgumentException("알수 없는 오류가 발생했습니다.");
        }
    }
}
