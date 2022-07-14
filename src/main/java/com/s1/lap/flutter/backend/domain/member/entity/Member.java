package com.s1.lap.flutter.backend.domain.member.entity;

import com.s1.lap.flutter.backend.domain.common.entity.BaseTime;
import com.s1.lap.flutter.backend.domain.event.entity.Event;
import com.s1.lap.flutter.backend.domain.post.entity.Post;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "profile_image_url")
    private String profileImageURL;

    @OneToMany(mappedBy = "member")
    private List<Relation> relationList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Event> eventList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

    protected Member() { /*empty*/ }

    @Builder
    public Member(Long id, String email, String password, String name, String phoneNumber, String profileImageURL, List<Relation> relationList, List<Event> eventList, List<Post> postList) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImageURL = profileImageURL;
        this.relationList = relationList;
        this.eventList = eventList;
        this.postList = postList;
    }
}
