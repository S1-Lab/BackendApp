package com.s1.lap.flutter.backend.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "is_sent")
    private Boolean isSent;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name = "memo")
    private String memo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected Post() { /*empty*/ }
}
