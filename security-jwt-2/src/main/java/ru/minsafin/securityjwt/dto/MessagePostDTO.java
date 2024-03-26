package ru.minsafin.securityjwt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePostDTO {
    @NotNull
    private Long userId;

    @NotNull
    private String text;
}
