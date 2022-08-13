package com.s1.lab.flutter.backend.domain.post.service;

import com.s1.lab.flutter.backend.domain.post.dto.PostCreateRequestDto;
import com.s1.lab.flutter.backend.domain.post.entity.Post;

import java.util.List;

public interface PostService {
    Post save(final PostCreateRequestDto requestDto);
    List<Post> findByMemberId(final Long memberId);
}
