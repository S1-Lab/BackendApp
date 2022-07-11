package com.s1.lap.flutter.backend.domain.post.controller;

import com.s1.lap.flutter.backend.configuration.util.ApiUtils.ApiResult;
import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.member.service.MemberService;
import com.s1.lap.flutter.backend.domain.post.dto.PostReadResponseDto;
import com.s1.lap.flutter.backend.domain.post.dto.PostReadRequestDto;
import com.s1.lap.flutter.backend.domain.post.dto.PostCreateRequestDto;
import com.s1.lap.flutter.backend.domain.post.dto.PostCreateResponseDto;
import com.s1.lap.flutter.backend.domain.post.dto.PostAddResponseDto;
import com.s1.lap.flutter.backend.domain.post.entity.Post;
import com.s1.lap.flutter.backend.domain.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.s1.lap.flutter.backend.configuration.util.ApiUtils.success;

@RestController
@Validated
@Slf4j
@RequestMapping("/post")
public class PostRestController {

    private final PostService postService;
    private final MemberService memberService;

    @Autowired
    public PostRestController(final PostService postService, final MemberService memberService) {
        this.postService = postService;
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public ApiResult<PostReadResponseDto> read(@Valid @PathVariable("id") PostReadRequestDto requestDto) {
        Member member = memberService.findById(requestDto.getMemberId());
        return success(PostReadResponseDto.of(member));
    }

    @PostMapping
    public ApiResult<PostCreateResponseDto> create(@Valid @RequestBody PostCreateRequestDto requestDto) {
        Post post = postService.save(requestDto);
        return success(PostCreateResponseDto.of(post));
    }

    @PostMapping("/{id}")
    public ApiResult<PostAddResponseDto> add(@Valid @PathVariable("id") PostReadRequestDto requestDto) {
        Member member = memberService.findById(requestDto.getMemberId());
        return success(PostAddResponseDto.of(member));
    }
}
