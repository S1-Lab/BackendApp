package com.s1.lap.flutter.backend.domain.event.service;

import com.s1.lap.flutter.backend.domain.event.dto.EventCreateRequestDto;
import com.s1.lap.flutter.backend.domain.event.dto.EventDeleteRequestDto;
import com.s1.lap.flutter.backend.domain.event.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> saveAndFindAll(final EventCreateRequestDto requestDto);
    List<Event> deleteAndFindAll(final EventDeleteRequestDto requestDto);
    List<Event> findByMemberId(final Long memberId);
}
