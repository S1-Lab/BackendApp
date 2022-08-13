package com.s1.lab.flutter.backend.domain.post.dto;

import com.s1.lab.flutter.backend.domain.member.entity.Member;
import com.s1.lab.flutter.backend.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostReadResponseDto {

    private List<Post> postList;

    protected PostReadResponseDto() {
        /* empty */
    }

    @Builder
    public PostReadResponseDto(final List<Post> postList) {
        this.postList = ((postList == null) ? new ArrayList<>() : postList);
    }

    public static PostReadResponseDto of(final Member member) {
        return PostReadResponseDto.builder()
                .postList(member.getPosts())
                .build();
    }
}
