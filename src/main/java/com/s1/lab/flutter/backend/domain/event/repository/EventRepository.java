package com.s1.lab.flutter.backend.domain.event.repository;

import com.s1.lab.flutter.backend.domain.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Object> {
    boolean existsByNameAndMemberId(final String name, final Long memberId);
    Optional<Event> findByNameAndMemberId(final String name, final Long memberId);
    List<Event> findByMemberId(final Long memberId);
}
