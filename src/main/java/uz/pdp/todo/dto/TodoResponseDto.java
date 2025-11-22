package uz.pdp.todo.dto;

public record TodoResponseDto(Long id, String title, String description, Boolean completed) { }
