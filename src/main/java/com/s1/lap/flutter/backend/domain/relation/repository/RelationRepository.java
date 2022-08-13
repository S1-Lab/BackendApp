package com.s1.lap.flutter.backend.domain.relation.repository;

import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Object> {
    boolean existsByNameAndMemberId(final String name, final Long memberId);
    Optional<Relation> findByNameAndMemberId(final String name, final Long memberId);
    List<Relation> findByMemberId(final Long memberId);
}
