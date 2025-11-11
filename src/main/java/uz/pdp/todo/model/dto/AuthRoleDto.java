package uz.pdp.todo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRoleDto {
    private String id;
    private String name;
    private String code;
}
