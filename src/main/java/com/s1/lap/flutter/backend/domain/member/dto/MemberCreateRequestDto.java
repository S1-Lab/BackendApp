package com.s1.lap.flutter.backend.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class MemberCreateRequestDto {

    @NotBlank(message = "Email을 입력해주세요.")
    @Email(message = "Email 형식이 옳바르지 않습니다.")
    private final String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private final String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private final String name;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private final String phoneNumber;

    private final String profileImageURL;

    @Builder
    public MemberCreateRequestDto(final String email, final String password, final String name, final String phoneNumber, final String profileImageURL) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImageURL = profileImageURL;
    }

    public MemberCreateRequestDto(final String email, final String password, final String name, final String phoneNumber) {
        this(email, password, name, phoneNumber, null);
    }
}
