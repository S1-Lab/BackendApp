package com.s1.lap.flutter.backend.domain.event.entity;

import com.s1.lap.flutter.backend.domain.user.entity.User;

import javax.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected Event() { /*empty*/ }
}
