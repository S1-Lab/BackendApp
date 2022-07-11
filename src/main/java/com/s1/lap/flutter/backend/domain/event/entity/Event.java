package com.s1.lap.flutter.backend.domain.event.entity;

import com.s1.lap.flutter.backend.domain.common.entity.BaseTime;
import com.s1.lap.flutter.backend.domain.member.entity.Member;
import com.s1.lap.flutter.backend.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Event extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "event")
    private Post post;

    protected Event() { /*empty*/ }

    @Builder
    public Event(Long id, String name, Member member, Post post) {
        this.id = id;
        this.name = name;
        this.member = member;
        this.post = post;
    }
}
