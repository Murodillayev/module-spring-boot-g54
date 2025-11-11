package uz.pdp.todo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRoleChangeDto {
    private String name;
    private String code;
}
