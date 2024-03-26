package ru.minsafin.securityjwt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePutDTO {
    @NotNull
    private Long id;
    @NotNull
    private String text;
}
