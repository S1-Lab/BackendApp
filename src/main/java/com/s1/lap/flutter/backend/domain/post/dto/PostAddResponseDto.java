package com.s1.lap.flutter.backend.domain.post.dto;

import com.s1.lap.flutter.backend.domain.event.entity.Event;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostAddResponseDto {

    private List<String> eventNames;
    private List<String> relationNames;

    protected PostAddResponseDto() {
        /* empty */
    }

    @Builder
    public PostAddResponseDto(final List<Event> events, final List<Relation> relations) {
        this.eventNames = events.stream()
                .map(Event::getName)
                .collect(Collectors.toList());
        this.relationNames = relations.stream()
                .map(Relation::getName)
                .collect(Collectors.toList());
    }

    public static PostAddResponseDto of(final List<Event> events, final List<Relation> relations) {
        return PostAddResponseDto.builder()
                .events(events)
                .relations(relations)
                .build();
    }
}
