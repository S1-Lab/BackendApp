package com.s1.lap.flutter.backend.domain.event.controller;

import com.s1.lap.flutter.backend.configuration.util.ApiUtils.ApiResult;
import com.s1.lap.flutter.backend.domain.event.dto.EventCreateRequestDto;
import com.s1.lap.flutter.backend.domain.event.dto.EventCreateResponseDto;
import com.s1.lap.flutter.backend.domain.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.s1.lap.flutter.backend.configuration.util.ApiUtils.success;

@RestController
@RequestMapping("/event")
@Validated
public class EventRestController {

    private final EventService eventService;

    @Autowired
    public EventRestController(final EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ApiResult<EventCreateResponseDto> create(@Valid @RequestBody EventCreateRequestDto requestDto) {
        return success(EventCreateResponseDto.of(eventService.save(requestDto)));
    }
}
