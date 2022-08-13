package com.s1.lap.flutter.backend.controller;

import com.s1.lap.flutter.backend.domain.event.controller.EventRestController;
import com.s1.lap.flutter.backend.domain.member.dto.MemberCreateRequestDto;
import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.member.service.MemberService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    private Member member;

    @BeforeAll
    void init() {
        MemberCreateRequestDto requestDto = MemberCreateRequestDto.builder()
                .email("eventTest@naver.com")
                .password("1234")
                .name("행사 테스트")
                .phoneNumber("123-1234-1234")
                .build();
        member = memberService.save(requestDto);
    }

    @Test
    @Order(1)
    @DisplayName("행사 생성")
    void createEvent() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"eventName\":\"이벤트1\",\"memberId\":\"" + member.getId() + "\"}")
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(EventRestController.class))
                .andExpect(handler().methodName("create"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.eventNames").exists());
    }

    @Test
    @Order(2)
    @DisplayName("행사 생성/삭제")
    void createAndDeleteEvent() throws Exception {
        ResultActions createResult = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"eventName\":\"이벤트1\",\"memberId\":\"" + member.getId() + "\"}")
        );
        createResult.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(EventRestController.class))
                .andExpect(handler().methodName("create"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.eventNames").exists());

        ResultActions deleteResult = mockMvc.perform(
                delete("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"eventName\":\"이벤트1\",\"memberId\":\"" + member.getId() + "\"}")
        );
        deleteResult.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(EventRestController.class))
                .andExpect(handler().methodName("delete"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.eventNames").exists());
    }

    @Test
    @Order(3)
    @DisplayName("행사 이름 공백 시 400 에러")
    void emptyEventName() throws Exception {
        ResultActions createResult = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"memberId\":\"" + member.getId() + "\"}")
        );
        createResult.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(handler().handlerType(EventRestController.class))
                .andExpect(handler().methodName("create"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.response.eventNames").doesNotExist());
    }

    @Test
    @Order(4)
    @DisplayName("회원 번호 공백 시 400 에러")
    void emptyMemberId() throws Exception {
        ResultActions createResult = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"eventName\":\"이벤트1\"}")
        );
        createResult.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(handler().handlerType(EventRestController.class))
                .andExpect(handler().methodName("create"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.response.eventNames").doesNotExist());
    }

    @Test
    @Order(5)
    @DisplayName("회원 번호 숫자가 아닌 입력시 400 에러")
    void invalidMemberId() throws Exception {
        ResultActions createResult = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"eventName\":\"이벤트1\",\"memberId\":\"문자입력\"}")
        );
        createResult.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(handler().handlerType(EventRestController.class))
                .andExpect(handler().methodName("create"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.response.eventNames").doesNotExist());
    }

    @Test
    @Order(6)
    @DisplayName("하나의 회원에 같은 행사 이름 중복 생성시 400에러")
    void duplicateEventName() throws Exception {
        ResultActions result1 = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"eventName\":\"이벤트1\",\"memberId\":\"" + member.getId() + "\"}")
        );
        result1.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(EventRestController.class))
                .andExpect(handler().methodName("create"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.eventNames").exists());

        ResultActions result2 = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"eventName\":\"이벤트1\",\"memberId\":\"" + member.getId() + "\"}")
        );
        result2.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(handler().handlerType(EventRestController.class))
                .andExpect(handler().methodName("create"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.response.eventNames").doesNotExist());
    }
}

