package com.s1.lab.flutter.backend.domain.post.controller;

import com.s1.lab.flutter.backend.configuration.util.ApiUtils.ApiResult;
import com.s1.lab.flutter.backend.domain.event.service.EventService;
import com.s1.lab.flutter.backend.domain.member.entity.Member;
import com.s1.lab.flutter.backend.domain.member.service.MemberService;
import com.s1.lab.flutter.backend.domain.post.dto.PostCreateRequestDto;
import com.s1.lab.flutter.backend.domain.post.dto.PostAddResponseDto;
import com.s1.lab.flutter.backend.domain.post.dto.PostCreateResponseDto;
import com.s1.lab.flutter.backend.domain.post.dto.PostReadResponseDto;
import com.s1.lab.flutter.backend.domain.post.entity.Post;
import com.s1.lab.flutter.backend.domain.post.service.PostService;
import com.s1.lab.flutter.backend.domain.relation.service.RelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;

import static com.s1.lab.flutter.backend.configuration.util.ApiUtils.success;

@RestController
@Validated
@Slf4j
@RequestMapping("/post")
public class PostRestController {

    private final PostService postService;
    private final MemberService memberService;
    private final EventService eventService;
    private final RelationService relationService;

    @Autowired
    public PostRestController(final PostService postService, final MemberService memberService, final EventService eventService, final RelationService relationService) {
        this.postService = postService;
        this.memberService = memberService;
        this.eventService = eventService;
        this.relationService = relationService;
    }

    @PostMapping("/{id}")
    public ApiResult<PostAddResponseDto> add(@PathVariable("id") Long memberId) {
        Member member = memberService.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));
        return success(PostAddResponseDto.of(eventService.findByMemberId(member.getId()), relationService.findByMemberId(member.getId())));
    }

    @PostMapping
    public ApiResult<PostCreateResponseDto> create(@Valid @RequestBody PostCreateRequestDto requestDto) {
        Post post = postService.save(requestDto);
        return success(PostCreateResponseDto.of(post));
    }

    @GetMapping("/{id}")
    public ApiResult<PostReadResponseDto> read(@PathVariable("id") Long memberId) {
        Member member = memberService.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));
        return success(PostReadResponseDto.of(member));
    }
}
