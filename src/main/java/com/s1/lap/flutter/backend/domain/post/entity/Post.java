package com.s1.lap.flutter.backend.domain.post.entity;

import com.s1.lap.flutter.backend.domain.common.entity.BaseTime;
import com.s1.lap.flutter.backend.domain.event.entity.Event;
import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "is_sent", nullable = false)
    private Boolean isSent;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    @Column(name = "memo")
    private String memo;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToOne
    @JoinColumn(name = "relation_id")
    private Relation relation;

    protected Post() { /*empty*/ }

    @Builder
    public Post(Long id, String name, String phoneNumber, Long amount, Boolean isSent, LocalDateTime sentAt, String memo, Member member, Event event, Relation relation) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.isSent = isSent;
        this.sentAt = sentAt;
        this.memo = memo;
        this.member = member;
        this.event = event;
        this.relation = relation;
    }
}
