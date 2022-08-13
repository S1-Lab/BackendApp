package com.s1.lab.flutter.backend.domain.post.repository;

import com.s1.lab.flutter.backend.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Object> {
    List<Post> findByMemberId(final Long memberId);
}
