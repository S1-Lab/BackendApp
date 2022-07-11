package com.s1.lap.flutter.backend.domain.relation.entity;

import com.s1.lap.flutter.backend.domain.user.entity.User;

import javax.persistence.*;

@Entity
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User post;

    protected Relation() { /*empty*/ }
}
