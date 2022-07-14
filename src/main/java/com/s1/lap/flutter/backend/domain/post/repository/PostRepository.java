package com.s1.lap.flutter.backend.domain.post.repository;

import com.s1.lap.flutter.backend.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Object> {
}
