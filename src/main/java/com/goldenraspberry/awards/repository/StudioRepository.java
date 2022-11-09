package com.goldenraspberry.awards.repository;

import com.goldenraspberry.awards.model.Studio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
    Optional<Studio> findByName(String name);
}