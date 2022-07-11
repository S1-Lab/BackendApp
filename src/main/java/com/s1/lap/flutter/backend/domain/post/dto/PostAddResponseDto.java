package com.s1.lap.flutter.backend.domain.post.dto;

import com.s1.lap.flutter.backend.domain.event.entity.Event;
import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostAddResponseDto {

    private List<Event> eventList;
    private List<Relation> relationList;

    protected PostAddResponseDto() {
        /* empty */
    }

    @Builder
    public PostAddResponseDto(final List<Event> eventList, final List<Relation> relationList) {
        this.eventList = ((eventList == null) ? new ArrayList<>() : eventList);
        this.relationList = ((relationList == null) ? new ArrayList<>() : relationList);
    }

    public static PostAddResponseDto of(final Member member) {
        return PostAddResponseDto.builder()
                .eventList(member.getEventList())
                .relationList(member.getRelationList())
                .build();
    }
}
