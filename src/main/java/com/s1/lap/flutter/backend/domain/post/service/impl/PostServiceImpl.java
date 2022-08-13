package com.s1.lap.flutter.backend.domain.post.service.impl;

import com.s1.lap.flutter.backend.domain.event.entity.Event;
import com.s1.lap.flutter.backend.domain.event.repository.EventRepository;
import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.member.repository.MemberRepository;
import com.s1.lap.flutter.backend.domain.post.dto.PostCreateRequestDto;
import com.s1.lap.flutter.backend.domain.post.entity.Post;
import com.s1.lap.flutter.backend.domain.post.repository.PostRepository;
import com.s1.lap.flutter.backend.domain.post.service.PostService;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import com.s1.lap.flutter.backend.domain.relation.repository.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;
    private final RelationRepository relationRepository;

    @Autowired
    public PostServiceImpl(final PostRepository postRepository, final MemberRepository memberRepository, final EventRepository eventRepository, final RelationRepository relationRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.eventRepository = eventRepository;
        this.relationRepository = relationRepository;
    }

    @Override
    @Transactional
    public Post save(final PostCreateRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        Event event = eventRepository.findById(requestDto.getEventId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이벤트 입니다."));
        Relation relation = relationRepository.findById(requestDto.getRelationId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관계 입니다."));
        Post post = Post.builder()
                .name(requestDto.getName())
                .phoneNumber(requestDto.getPhoneNumber())
                .amount(requestDto.getAmount())
                .isSent(requestDto.getIsSent())
                .sentAt(requestDto.getSentAt())
                .memo(requestDto.getMemo())
                .member(member)
                .event(event)
                .relation(relation)
                .build();
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public List<Post> findByMemberId(final Long memberId) {
        return postRepository.findByMemberId(memberId);
    }
}
