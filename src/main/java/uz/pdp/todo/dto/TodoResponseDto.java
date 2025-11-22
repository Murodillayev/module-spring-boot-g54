package uz.pdp.todo.dto;

import lombok.Getter;


public record TodoResponseDto(Long id, String title, String description, Boolean completed) { }
