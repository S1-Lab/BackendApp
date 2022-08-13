package com.s1.lap.flutter.backend.domain.member.entity;

import com.s1.lap.flutter.backend.domain.common.entity.BaseTime;
import com.s1.lap.flutter.backend.domain.event.entity.Event;
import com.s1.lap.flutter.backend.domain.post.entity.Post;
import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "profile_image_url")
    private String profileImageURL;

    @ToString.Exclude
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Relation> relations = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    protected Member() {
        /* empty */
    }

    @Builder
    public Member(final Long id, final String email, final String password, final String name, final String phoneNumber, final String profileImageURL, final List<Relation> relations, final List<Event> events, final List<Post> posts) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImageURL = profileImageURL;
        this.relations = (relations == null ? new ArrayList<>() : relations);
        this.events = (events == null ? new ArrayList<>() : events);
        this.posts = (posts == null ? new ArrayList<>() : posts);
    }

    public void addRelation(final Relation relation) {
        this.relations.add(relation);
        relation.setMember(this);
    }

    public void removeRelation(final Relation relation) {
        this.relations.remove(relation);
        relation.setMember(null);
    }

    public void addEvent(final Event event) {
        this.events.add(event);
        event.setMember(this);
    }

    public void removeEvent(final Event event) {
        this.events.remove(event);
        event.setMember(null);
    }

    public void addPost(final Post post) {
        this.posts.add(post);
        post.setMember(this);
    }

    public void removePost(final Post post) {
        this.posts.remove(post);
        post.setMember(null);
    }
}
