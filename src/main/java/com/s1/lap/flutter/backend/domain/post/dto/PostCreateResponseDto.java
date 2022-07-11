package com.s1.lap.flutter.backend.domain.post.dto;

import com.s1.lap.flutter.backend.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostCreateResponseDto {

    private String name;
    private String phoneNumber;
    private Long amount;
    private Boolean isSent;
    private LocalDateTime sentAt;
    private String memo;
    private String memberName;
    private String eventName;
    private String relationName;

    protected PostCreateResponseDto() {
        /* empty */
    }

    @Builder
    public PostCreateResponseDto(String name, String phoneNumber, Long amount, Boolean isSent, LocalDateTime sentAt, String memo, String memberName, String eventName, String relationName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.isSent = isSent;
        this.sentAt = sentAt;
        this.memo = memo;
        this.memberName = memberName;
        this.eventName = eventName;
        this.relationName = relationName;
    }

    public static PostCreateResponseDto of(final Post post) {
        return PostCreateResponseDto.builder()
                .name(post.getName())
                .phoneNumber(post.getPhoneNumber())
                .amount(post.getAmount())
                .isSent(post.getIsSent())
                .sentAt(post.getSentAt())
                .memo(post.getMemo())
                .memberName(post.getMember().getName())
                .eventName(post.getEvent().getName())
                .relationName(post.getRelation().getName())
                .build();
    }
}
