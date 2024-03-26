package ru.minsafin.securityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.minsafin.securityjwt.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
