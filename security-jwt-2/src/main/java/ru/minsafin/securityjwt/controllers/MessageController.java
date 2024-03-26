package ru.minsafin.securityjwt.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.minsafin.securityjwt.dto.MessageDeleteDTO;
import ru.minsafin.securityjwt.dto.MessagePostDTO;
import ru.minsafin.securityjwt.dto.MessagePutDTO;
import ru.minsafin.securityjwt.services.MessageService;
import ru.minsafin.securityjwt.utils.Converter;

@RestController
@RequestMapping("/topic/{id}/message")
@RequiredArgsConstructor
@Tag(name = "Сообщения")
public class MessageController {
    private final MessageService messageService;
    private final Converter converter;

    // TODO Только админы/создавшие пользователи могут использовать эти методы
    @PostMapping()
    public ResponseEntity<HttpStatus> createMessage(@PathVariable("id") Long id,
                                                    @RequestBody @Valid MessagePostDTO messagePostDTO) {
        messageService.save(converter.convertToMessage(messagePostDTO, id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> updateMessage(@PathVariable("id") Long id,
                                                    @RequestBody @Valid MessagePutDTO messagePostDTO) {
        messageService.update(id, messagePostDTO.getText());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteMessage(@PathVariable("id") Long id,
                                                    @RequestBody @Valid MessageDeleteDTO messageDeleteDTO) {
        messageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
