package com.s1.lap.flutter.backend.domain.event.repository;

import com.s1.lap.flutter.backend.domain.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Object> {
}
