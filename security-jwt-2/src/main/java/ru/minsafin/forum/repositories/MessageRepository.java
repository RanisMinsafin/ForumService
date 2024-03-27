package ru.minsafin.forum.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.minsafin.forum.models.Message;

import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findById(UUID id);

    void deleteById(UUID id);

    Page<Message> findAllByTopicId(UUID topicId, Pageable pageable);
}
