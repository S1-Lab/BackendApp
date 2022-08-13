package com.s1.lap.flutter.backend.domain.post.entity;

import com.s1.lap.flutter.backend.domain.common.entity.BaseTime;
import com.s1.lap.flutter.backend.domain.event.entity.Event;
import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Getter
@ToString
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
    private LocalDate sentAt;

    @Column(name = "memo")
    private String memo;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relation_id")
    private Relation relation;

    protected Post() { /*empty*/ }

    @Builder
    public Post(Long id, String name, String phoneNumber, Long amount, Boolean isSent, LocalDate sentAt, String memo, Member member, Event event, Relation relation) {
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

    public void setMember(Member member) {
        this.member = member;
    }
}
