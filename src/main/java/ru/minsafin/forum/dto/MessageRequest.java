package ru.minsafin.forum.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    @NotNull
    private UUID userId;
    private UUID topicId;
    private String text;
}
