package com.s1.lap.flutter.backend.domain.event.dto;

import com.s1.lap.flutter.backend.domain.event.entity.Event;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventDeleteResponseDto {

    private List<String> eventNames;

    protected EventDeleteResponseDto() {
        /* empty */
    }

    @Builder
    public EventDeleteResponseDto(final List<Event> events) {
        this.eventNames = events.stream()
                .map(Event::getName)
                .collect(Collectors.toList());
    }

    public static EventDeleteResponseDto of(final List<Event> events) {
        return EventDeleteResponseDto.builder()
                .events(events)
                .build();
    }
}
