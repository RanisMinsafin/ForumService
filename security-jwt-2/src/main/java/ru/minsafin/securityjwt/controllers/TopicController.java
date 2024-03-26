package ru.minsafin.securityjwt.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.minsafin.securityjwt.dto.MessagePostDTO;
import ru.minsafin.securityjwt.dto.TopicDTO;
import ru.minsafin.securityjwt.services.TopicService;
import ru.minsafin.securityjwt.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
@Tag(name = "Темы")
public class TopicController {
    private final TopicService topicService;
    private final Converter converter;

    @GetMapping()
    public List<TopicDTO> getAllTopics() {
        return topicService.getAllTopics()
                .stream()
                .map(converter::convertToTopicDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<MessagePostDTO> getMessages(@PathVariable("id") Long id) {
        return topicService.getById(id).getMessages()
                .stream()
                .map(converter::convertToMessageDTO)
                .collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createTopic(@RequestBody @Valid TopicDTO topicDTO) {
        topicService.save(converter.convertToTopic(topicDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // TODO Только админы/создавшие пользователи могут использовать эти методы

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTopic(@PathVariable("id") Long id,
                                                  @RequestBody @Valid TopicDTO topicDTO) {
        topicService.update(id, converter.convertToTopic(topicDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTopic(@PathVariable("id") Long id,
                                                  @RequestBody @Valid TopicDTO topicDTO) {
        topicService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
