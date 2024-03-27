package ru.minsafin.forum.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.minsafin.forum.exceptions.TopicNotCreatedException;
import ru.minsafin.forum.exceptions.TopicNotFoundException;
import ru.minsafin.forum.models.Topic;
import ru.minsafin.forum.repositories.TopicRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TopicService {
    private final TopicRepository repository;

    public UUID save(Topic topic) {
        return repository.save(topic).getId();
    }

    public void create(Topic topic) {
        if (repository.existsByName(topic.getName())) {
            throw new TopicNotCreatedException("Тема с таким именем уже существует");
        }
        this.save(topic);
    }

    public Topic getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException("Тема с таким id не найдена"));
    }

    public Page<Topic> getAllTopics(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void delete(UUID id) {
        Optional<Topic> topicOptional = repository.findById(id);
        if (topicOptional.isEmpty()) {
            throw new TopicNotFoundException("Тема с таким id не найдена");
        }
        repository.delete(topicOptional.get());
    }

    public void update(UUID id, Topic updatedTopic) {
        Optional<Topic> topicOptional = repository.findById(id);
        if (topicOptional.isEmpty()) {
            throw new TopicNotFoundException("Тема с таким id не найдена");
        }
        Topic topic = topicOptional.get();
        topic.setName(updatedTopic.getName());
        repository.save(topic);
    }
}
