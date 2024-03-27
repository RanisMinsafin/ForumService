package ru.minsafin.forum.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.minsafin.forum.dto.MessageRequest;
import ru.minsafin.forum.dto.MessageResponse;
import ru.minsafin.forum.dto.TopicRequest;
import ru.minsafin.forum.dto.TopicResponse;
import ru.minsafin.forum.services.MessageService;
import ru.minsafin.forum.services.TopicService;
import ru.minsafin.forum.utils.Converter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("topics")
@RequiredArgsConstructor
@Tag(name = "Темы")
public class TopicController {
    private final TopicService topicService;
    private final MessageService messageService;
    private final Converter converter;

    @Operation(summary = "Все темы")
    @GetMapping()
    public List<TopicResponse> getAllTopics(@RequestParam(defaultValue = "0") int pageNo,
                                            @RequestParam(defaultValue = "5") int pageSize) {
        return topicService.getAllTopics(PageRequest.of(pageNo, pageSize))
                .stream()
                .map(converter::convertToTopicResponse)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Все сообщения по теме")
    @GetMapping("/{id}")
    public List<MessageResponse> getMessages(@PathVariable("id") UUID id,
                                             @RequestParam(defaultValue = "0") int pageNo,
                                             @RequestParam(defaultValue = "5") int pageSize) {
        return messageService.getAllByTopicId(id, PageRequest.of(pageNo, pageSize))
                .stream()
                .map(converter::converToMessageResponse)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Создание темы")
    @PostMapping()
    public ResponseEntity<HttpStatus> createTopic(@RequestBody @Valid TopicRequest topicRequest) {
        UUID id = topicService.save(converter.convertToTopic(topicRequest));
        MessageRequest messageRequest = topicRequest.getMessageRequest();
        messageService.save(converter.convertToMessage(messageRequest, id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
