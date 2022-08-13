package com.s1.lab.flutter.backend.domain.event.entity;

import com.s1.lab.flutter.backend.domain.common.entity.BaseTime;
import com.s1.lab.flutter.backend.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Getter
@ToString
public class Event extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private Member member;

    protected Event() { /*empty*/ }

    @Builder
    public Event(Long id, String name, Member member) {
        this.id = id;
        this.name = name;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
