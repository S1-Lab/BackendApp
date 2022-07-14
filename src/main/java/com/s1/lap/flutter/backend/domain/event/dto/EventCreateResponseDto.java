package com.s1.lap.flutter.backend.domain.event.dto;

import com.s1.lap.flutter.backend.domain.event.entity.Event;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EventCreateResponseDto {

    private Long eventId;
    private String eventName;

    protected EventCreateResponseDto() {
        /* empty */
    }

    @Builder
    public EventCreateResponseDto(final Long eventId, final String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
    }

    public static EventCreateResponseDto of(final Event event){
        return EventCreateResponseDto.builder()
                .eventId(event.getId())
                .eventName(event.getName())
                .build();
    }
}
