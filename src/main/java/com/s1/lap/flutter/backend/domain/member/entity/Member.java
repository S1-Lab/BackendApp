package com.s1.lap.flutter.backend.domain.member.entity;

import com.s1.lap.flutter.backend.domain.event.entity.Event;
import com.s1.lap.flutter.backend.domain.post.entity.Post;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import com.s1.lap.flutter.backend.domain.common.entity.BaseTime;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
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
}
