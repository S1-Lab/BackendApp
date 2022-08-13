package com.s1.lap.flutter.backend.domain.post.dto;

import com.s1.lap.flutter.backend.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
public class PostCreateResponseDto {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    @Min(value = 1L, message = "제한된 범위 밖입니다.")
    @Max(value = Long.MAX_VALUE, message = "제한된 범위 밖입니다.")
    private Long amount;

    @NotBlank(message = "송/수금 상태를 입력해주세요.")
    private Boolean isSent;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate sentAt;

    private String memo;

    @NotBlank(message = "행사명을 입력해주세요.")
    private String eventName;

    @NotBlank(message = "관계명을 입력해주세요.")
    private String relationName;

    protected PostCreateResponseDto() {
        /* empty */
    }

    @Builder
    public PostCreateResponseDto(String name, String phoneNumber, Long amount, Boolean isSent, LocalDate sentAt, String memo, String eventName, String relationName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.isSent = isSent;
        this.sentAt = sentAt;
        this.memo = memo;
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
                .eventName(post.getEvent().getName())
                .relationName(post.getRelation().getName())
                .build();
    }
}
