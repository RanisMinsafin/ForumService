package ru.minsafin.forum.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.minsafin.forum.dto.MessageRequest;
import ru.minsafin.forum.dto.MessageResponse;
import ru.minsafin.forum.dto.TopicRequest;
import ru.minsafin.forum.dto.TopicResponse;
import ru.minsafin.forum.models.Message;
import ru.minsafin.forum.models.Topic;
import ru.minsafin.forum.services.TopicService;
import ru.minsafin.forum.services.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Converter {
    private ModelMapper modelMapper;
    private UserService userService;
    private TopicService topicService;

    @Autowired
    public Converter(ModelMapper modelMapper, UserService userService, TopicService topicService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.topicService = topicService;
    }

    public TopicResponse convertToTopicResponse(Topic topic) {
        return modelMapper.map(topic, TopicResponse.class);
    }

    public Topic convertToTopic(TopicRequest topicRequest) {
        return modelMapper.map(topicRequest, Topic.class);
    }

    public MessageResponse converToMessageResponse(Message message) {
        MessageResponse messageResponse = modelMapper.map(message, MessageResponse.class);
        messageResponse.setUsername(message.getUser().getUsername());
        return messageResponse;
    }

    public Message convertToMessage(MessageRequest messageRequest) {
        Message message = new Message();
        message.setText(messageRequest.getText());
        return enrichMessage(message, messageRequest);
    }

    public Message convertToMessage(MessageRequest messageRequest, UUID id) {
        messageRequest.setTopicId(id);
        return this.convertToMessage(messageRequest);
    }

    private Message enrichMessage(Message message, MessageRequest messageRequest) {
        message.setUser(userService.getById(messageRequest.getUserId()));
        message.setTopic(topicService.getById(messageRequest.getTopicId()));
        message.setDateTime(LocalDateTime.now());
        return message;
    }
}
