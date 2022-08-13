package com.s1.lap.flutter.backend.domain.relation.controller;

import com.s1.lap.flutter.backend.configuration.util.ApiUtils.ApiResult;
import com.s1.lap.flutter.backend.domain.relation.dto.RelationCreateRequestDto;
import com.s1.lap.flutter.backend.domain.relation.dto.RelationCreateResponseDto;
import com.s1.lap.flutter.backend.domain.relation.dto.RelationDeleteRequestDto;
import com.s1.lap.flutter.backend.domain.relation.dto.RelationDeleteResponseDto;
import com.s1.lap.flutter.backend.domain.relation.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;

import static com.s1.lap.flutter.backend.configuration.util.ApiUtils.success;

@RestController
@RequestMapping("/relation")
@Validated
public class RelationRestController {

    private final RelationService relationService;

    @Autowired
    public RelationRestController(RelationService relationService) {
        this.relationService = relationService;
    }

    @PostMapping
    public ApiResult<RelationCreateResponseDto> create(@Valid @RequestBody RelationCreateRequestDto requestDto) {
        return success(RelationCreateResponseDto.of(relationService.saveAndFindAll(requestDto)));
    }

    @DeleteMapping
    public ApiResult<RelationDeleteResponseDto> delete(@Valid @RequestBody RelationDeleteRequestDto requestDto) {
        return success(RelationDeleteResponseDto.of(relationService.deleteAndFindAll(requestDto)));
    }
}
