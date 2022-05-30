package com.s1.lap.flutter.backend.entity;

import com.s1.lap.flutter.backend.entity.util.BaseTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "profile_image_url")
    private String profileImageURL;

    @ManyToOne
    @JoinColumn(name = "relation_id")
    private Relation relation;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    protected User() { /*empty*/ }
}
