package com.s1.lab.flutter.backend.domain.event.dto;

import com.s1.lab.flutter.backend.domain.event.entity.Event;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventCreateResponseDto {

    private List<String> eventNames;

    protected EventCreateResponseDto() {
        /* empty */
    }

    @Builder
    public EventCreateResponseDto(final List<Event> events) {
        this.eventNames = events.stream()
                .map(Event::getName)
                .collect(Collectors.toList());
    }

    public static EventCreateResponseDto of(final List<Event> events) {
        return EventCreateResponseDto.builder()
                .events(events)
                .build();
    }
}
