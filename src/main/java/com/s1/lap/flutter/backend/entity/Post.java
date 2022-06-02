package com.s1.lap.flutter.backend.entity;

import com.s1.lap.flutter.backend.entity.util.BaseTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

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
    @JoinColumn(name = "user_id")
    private User user;

    protected Post() { /*empty*/ }
}
