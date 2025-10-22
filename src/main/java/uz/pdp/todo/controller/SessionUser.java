package uz.pdp.todo.controller;

import lombok.Getter;

import java.util.UUID;

@Getter
public class SessionUser {
    private String id = UUID.randomUUID().toString();
}
