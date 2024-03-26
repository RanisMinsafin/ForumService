package ru.minsafin.securityjwt.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.minsafin.securityjwt.dto.MessagePostDTO;
import ru.minsafin.securityjwt.dto.TopicDTO;
import ru.minsafin.securityjwt.models.Message;
import ru.minsafin.securityjwt.models.Topic;
import ru.minsafin.securityjwt.services.TopicService;
import ru.minsafin.securityjwt.services.UserService;

import java.time.LocalDateTime;

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

    public TopicDTO convertToTopicDTO(Topic topic) {
        return modelMapper.map(topic, TopicDTO.class);
    }

    public Topic convertToTopic(TopicDTO topicDTO) {
        return modelMapper.map(topicDTO, Topic.class);
    }

    public MessagePostDTO convertToMessageDTO(Message message) {
        return modelMapper.map(message, MessagePostDTO.class);
    }

    public Message convertToMessage(MessagePostDTO messagePostDTO, Long id) {
        Message message = modelMapper.map(messagePostDTO, Message.class);
        return enrichMessage(message, id, messagePostDTO.getUserId());
    }

    private Message enrichMessage(Message message, Long topicId, Long userId) {
        message.setUser(userService.getById(userId));
        message.setTopic(topicService.getById(topicId));
        message.setDateTime(LocalDateTime.now());
        return message;
    }

}
