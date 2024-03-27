package ru.minsafin.forum.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    @NotNull
    private String username;
    @NotNull
    private String text;
    @NotNull
    private LocalDateTime date;
}
