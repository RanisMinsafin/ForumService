package ru.minsafin.securityjwt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ c ошибкой")
public class EntityErrorResponse {
    private String message;
    private long timestamp;
}
