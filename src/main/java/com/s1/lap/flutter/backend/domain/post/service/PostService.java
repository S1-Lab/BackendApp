package com.s1.lap.flutter.backend.domain.post.service;

import com.s1.lap.flutter.backend.domain.post.dto.PostCreateRequestDto;
import com.s1.lap.flutter.backend.domain.post.entity.Post;

public interface PostService {
    Post save(final PostCreateRequestDto requestDto);
}
