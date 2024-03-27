package ru.minsafin.forum.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.minsafin.forum.models.Topic;

import java.util.Optional;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findById(UUID id);

    boolean existsByName(String name);

    Page<Topic> findAll(Pageable pageable);

}
