package com.s1.lab.flutter.backend.domain.member.repository;

import com.s1.lab.flutter.backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Object> {
    boolean existsByEmail(final String email);

    @EntityGraph(attributePaths = {"events"})
    Optional<Member> findWithEventsById(final Long id);

    @EntityGraph(attributePaths = {"relations"})
    Optional<Member> findWithRelationsById(final Long id);
}
