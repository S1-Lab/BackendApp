package com.s1.lap.flutter.backend.domain.event.service.impl;

import com.s1.lap.flutter.backend.domain.event.dto.EventCreateRequestDto;
import com.s1.lap.flutter.backend.domain.event.entity.Event;
import com.s1.lap.flutter.backend.domain.event.repository.EventRepository;
import com.s1.lap.flutter.backend.domain.event.service.EventService;
import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public EventServiceImpl(final EventRepository eventRepository, final MemberRepository memberRepository) {
        this.eventRepository = eventRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Event save(final EventCreateRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        Event event = Event.builder()
                .name(requestDto.getEventName())
                .member(member)
                .build();
        return eventRepository.save(event);
    }

    @Override
    @Transactional(readOnly = true)
    public Event findById(final Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이벤트 입니다."));
    }
}
