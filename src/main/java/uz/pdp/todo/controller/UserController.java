package uz.pdp.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.model.dto.AppErrorDto;
import uz.pdp.todo.model.dto.AuthUserCreateDto;
import uz.pdp.todo.model.dto.AuthUserDto;
import uz.pdp.todo.model.dto.AuthUserUpdateDto;
import uz.pdp.todo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "Foydalanuvchilar bilan ishlash uchun apilar")
public class UserController {

    private final UserService service;


    @PostMapping
    @Operation(summary = "Bu api user yaratdi", description = "User yaratishini chaynab tushuntiriladi bu yerda")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Muvaffaqiyatli topildi",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthUserDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Noto'g'ri so'rov",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AppErrorDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Ruxsat etilmagan",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AppErrorDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Foydalanuvchi topilmadi",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AppErrorDto.class)
                    )
            )
    })
    public ResponseEntity<AuthUserDto> create(@RequestBody AuthUserCreateDto dto) {
        AuthUserDto authUserDto = service.create(dto);
        return new ResponseEntity<>(authUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthUserDto> update(@RequestBody AuthUserUpdateDto dto, @PathVariable String id) {
        AuthUserDto authUserDto = service.update(id, dto);
        return new ResponseEntity<>(authUserDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthUserDto> updateIgnoreNull(@RequestBody AuthUserUpdateDto dto, @PathVariable String id) {
        AuthUserDto authUserDto = service.updateIgnoreNull(id, dto);
        return new ResponseEntity<>(authUserDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthUserDto> get(@PathVariable String id) {
        AuthUserDto authUserDto = service.get(id);
        return new ResponseEntity<>(authUserDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthUserDto>> getAll() {
        List<AuthUserDto> users = service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
