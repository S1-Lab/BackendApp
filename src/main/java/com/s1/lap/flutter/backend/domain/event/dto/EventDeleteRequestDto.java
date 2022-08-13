package com.s1.lap.flutter.backend.domain.event.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class EventDeleteRequestDto {

    @NotBlank(message = "행사명을 입력해주세요.")
    private String eventName;

    @Min(value = 1L, message = "제한된 범위 밖입니다.")
    @Max(value = Long.MAX_VALUE, message = "제한된 범위 밖입니다.")
    @NotNull(message = "회원 번호를 입력해주세요.")
    private Long memberId;

    protected EventDeleteRequestDto() {
        /* empty */
    }

    @Builder
    public EventDeleteRequestDto(final String eventName, final Long memberId) {
        this.eventName = eventName;
        this.memberId = memberId;
    }
}
