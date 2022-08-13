package com.s1.lab.flutter.backend.domain.event.service.impl;

import com.s1.lab.flutter.backend.domain.event.dto.EventCreateRequestDto;
import com.s1.lab.flutter.backend.domain.event.dto.EventDeleteRequestDto;
import com.s1.lab.flutter.backend.domain.event.entity.Event;
import com.s1.lab.flutter.backend.domain.event.repository.EventRepository;
import com.s1.lab.flutter.backend.domain.event.service.EventService;
import com.s1.lab.flutter.backend.domain.member.entity.Member;
import com.s1.lab.flutter.backend.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Event> saveAndFindAll(final EventCreateRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        if (eventRepository.existsByNameAndMemberId(requestDto.getEventName(), requestDto.getMemberId())) {
            throw new IllegalArgumentException("이미 존재하는 이벤트 입니다.");
        }
        Event event = eventRepository.save(
                Event.builder()
                        .name(requestDto.getEventName())
                        .member(member)
                        .build());
        member.addEvent(event);
        return eventRepository.findByMemberId(member.getId());
    }

    @Override
    @Transactional
    public List<Event> deleteAndFindAll(final EventDeleteRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));
        Event event = eventRepository.findByNameAndMemberId(requestDto.getEventName(), requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이벤트 입니다."));
        member.removeEvent(event);
        return eventRepository.findByMemberId(member.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findByMemberId(final Long memberId) {
        return eventRepository.findByMemberId(memberId);
    }
}
