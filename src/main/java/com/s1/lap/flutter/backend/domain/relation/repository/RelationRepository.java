package com.s1.lap.flutter.backend.domain.relation.repository;

import com.s1.lap.flutter.backend.domain.relation.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Object> {
}
