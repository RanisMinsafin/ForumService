package ru.minsafin.forum.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.minsafin.forum.dto.MessageRequest;
import ru.minsafin.forum.models.Message;
import ru.minsafin.forum.services.MessageService;
import ru.minsafin.forum.utils.Converter;

import java.util.UUID;

@RestController
@RequestMapping("messages")
@RequiredArgsConstructor
@Tag(name = "Сообщения")
public class MessageController {
    private final MessageService messageService;
    private final Converter converter;
    @Operation(summary = "Сохранение сообщения")
    @PostMapping()
    public ResponseEntity<HttpStatus> createMessage(@RequestBody @Valid MessageRequest messageRequest) {
        messageService.save(converter.convertToMessage(messageRequest));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Изменение сообщения")
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateMessage(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid MessageRequest messageRequest) {
        Message message = converter.convertToMessage(messageRequest);
        messageService.update(id, message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Удаление сообщения")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMessage(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid MessageRequest messageRequest) {
        messageService.delete(id, messageRequest.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
