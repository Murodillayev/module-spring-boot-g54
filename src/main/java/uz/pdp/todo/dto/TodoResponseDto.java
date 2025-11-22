package uz.pdp.todo.dto;

import java.io.Serializable;

public record TodoResponseDto(Long id, String title, String description, Boolean completed) implements Serializable { }
