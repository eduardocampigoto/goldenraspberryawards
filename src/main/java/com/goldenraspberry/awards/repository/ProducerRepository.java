package com.goldenraspberry.awards.repository;

import com.goldenraspberry.awards.model.Producer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Optional<Producer> findByName(String name);
}