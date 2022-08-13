package com.s1.lap.flutter.backend.domain.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostCreateRequestDto {

    private String name;
    private String phoneNumber;
    private Long amount;
    private Boolean isSent;
    private LocalDate sentAt;
    private String memo;
    private Long memberId;
    private Long eventId;
    private Long relationId;

    protected PostCreateRequestDto() {
        /* empty */
    }

    @Builder
    public PostCreateRequestDto(String name, String phoneNumber, Long amount, Boolean isSent, LocalDate sentAt, String memo, Long memberId, Long eventId, Long relationId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.isSent = isSent;
        this.sentAt = sentAt;
        this.memo = memo;
        this.memberId = memberId;
        this.eventId = eventId;
        this.relationId = relationId;
    }
}
