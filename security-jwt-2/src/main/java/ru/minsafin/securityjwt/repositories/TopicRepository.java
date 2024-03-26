package ru.minsafin.securityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.minsafin.securityjwt.models.Topic;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByName(String name);

    boolean existsByName(String name);
}
