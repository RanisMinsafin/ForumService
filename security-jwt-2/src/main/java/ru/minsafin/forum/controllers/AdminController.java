package ru.minsafin.forum.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.minsafin.forum.dto.MessageRequest;
import ru.minsafin.forum.dto.TopicRequest;
import ru.minsafin.forum.models.Message;
import ru.minsafin.forum.services.MessageService;
import ru.minsafin.forum.services.TopicService;
import ru.minsafin.forum.utils.Converter;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@Tag(name = "Администратор")
public class AdminController {
    private final TopicService topicService;
    private final MessageService messageService;
    private final Converter converter;

    @PutMapping("/topics/{id}")
    @Operation(summary = "Изменение темы")
    public ResponseEntity<HttpStatus> updateTopic(@PathVariable("id") UUID id,
                                                  @RequestBody @Valid TopicRequest topicRequest) {
        topicService.update(id, converter.convertToTopic(topicRequest));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/topics/{id}")
    @Operation(summary = "Удаление темы")
    public ResponseEntity<HttpStatus> deleteTopic(@PathVariable("id") UUID id) {
        topicService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/messages/{id}")
    @Operation(summary = "Изменение сообщения")
    public ResponseEntity<HttpStatus> updateMessage(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid MessageRequest messageRequest) {
        Message message = converter.convertToMessage(messageRequest);
        messageService.update(id, message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/messages/{id}")
    @Operation(summary = "Удаление сообщения")
    public ResponseEntity<HttpStatus> deleteMessage(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid MessageRequest messageRequest) {
        messageService.delete(id, messageRequest.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
