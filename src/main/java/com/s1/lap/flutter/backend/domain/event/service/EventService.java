package com.s1.lap.flutter.backend.domain.event.service;

import com.s1.lap.flutter.backend.domain.event.dto.EventCreateRequestDto;
import com.s1.lap.flutter.backend.domain.event.entity.Event;

public interface EventService {
    Event save(final EventCreateRequestDto requestDto);
    Event findById(final Long id);
}
