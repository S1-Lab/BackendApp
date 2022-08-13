package com.s1.lab.flutter.backend.controller;

import com.s1.lab.flutter.backend.domain.event.dto.EventCreateRequestDto;
import com.s1.lab.flutter.backend.domain.event.entity.Event;
import com.s1.lab.flutter.backend.domain.event.service.EventService;
import com.s1.lab.flutter.backend.domain.member.dto.MemberCreateRequestDto;
import com.s1.lab.flutter.backend.domain.member.entity.Member;
import com.s1.lab.flutter.backend.domain.member.service.MemberService;
import com.s1.lab.flutter.backend.domain.post.controller.PostRestController;
import com.s1.lab.flutter.backend.domain.relation.dto.RelationCreateRequestDto;
import com.s1.lab.flutter.backend.domain.relation.entity.Relation;
import com.s1.lab.flutter.backend.domain.relation.service.RelationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    @Autowired
    private EventService eventService;

    @Autowired
    private RelationService relationService;

    private Member member;

    private List<Event> events;

    private List<Relation> relations;

    @BeforeAll
    void init() {
        MemberCreateRequestDto requestDto = MemberCreateRequestDto.builder()
                .email("postTest@naver.com")
                .password("1234")
                .name("게시글 테스트")
                .phoneNumber("123-1234-1234")
                .build();
        member = memberService.save(requestDto);
        events = eventService.saveAndFindAll(
                EventCreateRequestDto.builder()
                        .eventName("행사1")
                        .memberId(member.getId())
                        .build()
        );
        relations = relationService.saveAndFindAll(
                RelationCreateRequestDto.builder()
                        .relationName("관계1")
                        .memberId(member.getId())
                        .build()
        );
    }

    @Test
    @Order(1)
    @DisplayName("게시글 추가 버튼 클릭 시")
    void addPost() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/post/" + member.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PostRestController.class))
                .andExpect(handler().methodName("add"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.eventNames").exists())
                .andExpect(jsonPath("$.response.relationNames").exists());
    }

    @Test
    @Order(2)
    @DisplayName("게시글 저장")
    void createPost() throws Exception {
        ResultActions postCreateResult = mockMvc.perform(
                post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"" +
                                ",\"phoneNumber\":\"010-1234-1234\"" +
                                ",\"isSent\":\"true\"" +
                                ",\"sentAt\":\"2022-05-10\"" +
                                ",\"memo\":\"메모메모메모\"" +
                                ",\"amount\":\"50000\"" +
                                ",\"memberId\":\"" + member.getId() + "\"" +
                                ",\"eventId\":\"" + events.get(0).getId() + "\"" +
                                ",\"relationId\":\"" + relations.get(0).getId() + "\"" +
                                "}")
        );
        postCreateResult.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PostRestController.class))
                .andExpect(handler().methodName("create"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.name").exists())
                .andExpect(jsonPath("$.response.phoneNumber").exists())
                .andExpect(jsonPath("$.response.amount").exists())
                .andExpect(jsonPath("$.response.isSent").exists())
                .andExpect(jsonPath("$.response.sentAt").exists())
                .andExpect(jsonPath("$.response.memo").exists())
                .andExpect(jsonPath("$.response.eventName").exists())
                .andExpect(jsonPath("$.response.relationName").exists());
    }

    @Test
    @Order(3)
    @DisplayName("게시글 추가 버튼 클릭 시 잘못된 회원 식별자 입력")
    void invalidAddPost() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/post/rrrr")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(handler().handlerType(PostRestController.class))
                .andExpect(handler().methodName("add"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.response.eventNames").doesNotExist())
                .andExpect(jsonPath("$.response.relationNames").doesNotExist());
    }


    @Test
    @Order(4)
    @DisplayName("게시글 저장 실패")
    void invalidCreatePost() throws Exception {
        ResultActions postCreateResult = mockMvc.perform(
                post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"" +
                                ",\"phoneNumber\":\"010-1234-1234\"" +
                                ",\"amount\":\"50000\"" +
                                ",\"isSent\":\"true\"" +
                                ",\"sentAt\":\"2022-05-10\"" +
                                ",\"memo\":\"메모메모메모\"" +
                                ",\"amount\":\"50000\"" +
                                ",\"memberId\":\"" + member.getId() + "\"" +
                                ",\"eventId\":\"" + events.get(0).getId() + "\"" +
                                ",\"relationId\":\"" + relations.get(0).getId() + "\"" +
                                "}")
        );
        postCreateResult.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PostRestController.class))
                .andExpect(handler().methodName("create"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.name").exists())
                .andExpect(jsonPath("$.response.phoneNumber").exists())
                .andExpect(jsonPath("$.response.amount").exists())
                .andExpect(jsonPath("$.response.isSent").exists())
                .andExpect(jsonPath("$.response.sentAt").exists())
                .andExpect(jsonPath("$.response.memo").exists())
                .andExpect(jsonPath("$.response.eventName").exists())
                .andExpect(jsonPath("$.response.relationName").exists());
    }
}
