package ru.minsafin.securityjwt.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.minsafin.securityjwt.exceptions.TopicNotCreatedException;
import ru.minsafin.securityjwt.exceptions.TopicNotFoundException;
import ru.minsafin.securityjwt.models.Topic;
import ru.minsafin.securityjwt.repositories.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository repository;

    public Topic save(Topic topic) {
        return repository.save(topic);
    }

    public void create(Topic topic) {
        if (repository.existsByName(topic.getName())) {
            throw new TopicNotCreatedException("Тема с таким именем уже существует");
        }
        this.save(topic);
    }

    public Topic getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException("Тема с таким id не найдена"));
    }

    public List<Topic> getAllTopics() {
        return repository.findAll();
    }

    public void delete(Long id) {
        Optional<Topic> topicOptional = repository.findById(id);
        if (topicOptional.isEmpty()) {
            throw new TopicNotFoundException("Тема с таким id не найдена");
        }
        repository.delete(topicOptional.get());
    }

    public void update(Long id, Topic updatedTopic) {
        Optional<Topic> topicOptional = repository.findById(id);
        if (topicOptional.isEmpty()) {
            throw new TopicNotFoundException("Тема с таким id не найдена");
        }
        Topic topic = topicOptional.get();
        topic.setName(updatedTopic.getName());
        repository.save(topic);
    }
}
