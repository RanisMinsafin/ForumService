package ru.minsafin.forum.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.minsafin.forum.exceptions.MessageNotFoundException;
import ru.minsafin.forum.models.Message;
import ru.minsafin.forum.models.Role;
import ru.minsafin.forum.repositories.MessageRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageRepository repository;
    private final UserService userService;

    public void save(Message message) {
        repository.save(message);
    }

    public void delete(UUID id, Long userId) {
        Optional<Message> messageOptional = getMessageById(id);
        Role role = userService.getById(userId).getRole();
        if (role != Role.ROLE_ADMIN) {
            checkIfUserCanEditMessage(messageOptional.get(), userId);
        }
        repository.deleteById(id);
    }

    public void update(UUID id, Message message) {
        Optional<Message> messageOptional = getMessageById(id);
        Role role = message.getUser().getRole();
        if (role != Role.ROLE_ADMIN) {
            checkIfUserCanEditMessage(messageOptional.get(), message.getUser().getId());
        }
        messageOptional.get().setText(message.getText());
    }

    private Optional<Message> getMessageById(UUID id) {
        Optional<Message> messageOptional = repository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new MessageNotFoundException("Сообщение с таким id не найдено");
        }
        return messageOptional;
    }

    private void checkIfUserCanEditMessage(Message message, Long userId) {
        if (!message.getUser().getId().equals(userId)) {
            throw new MessageNotFoundException("Вы не можете изменить сообщение другого пользователя");
        }
    }

    public Page<Message> getAllByTopicId(UUID topicId, Pageable pageable) {
        return repository.findAllByTopicId(topicId, pageable);
    }
}
