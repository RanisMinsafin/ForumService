package ru.minsafin.securityjwt.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.minsafin.securityjwt.exceptions.TopicNotFoundException;
import ru.minsafin.securityjwt.models.Message;
import ru.minsafin.securityjwt.repositories.MessageRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository repository;

    public void save(Message message) {
        repository.save(message);
    }

    public void delete(Long id) {
        Optional<Message> optionalMessage = repository.findById(id);
        if (optionalMessage.isEmpty()) {
            throw new TopicNotFoundException("Сообщение с таким id не найдено");
        }
        repository.delete(optionalMessage.get());
    }

    public void update(Long id, String text) {
        Optional<Message> messageOptional = repository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new TopicNotFoundException("Сообщение с таким id не найдено");
        }
        Message message = messageOptional.get();
        message.setText(text);
        repository.save(message);
    }
}
