package ru.minsafin.forum.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.minsafin.forum.services.UserService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Получение роли администратора")
public class RoleController {
    private final UserService service;

    @GetMapping("/get-admin")
    @Operation(summary = "Получить роль ADMIN")
    public void getAdmin() {
        service.getAdmin();
    }
}
