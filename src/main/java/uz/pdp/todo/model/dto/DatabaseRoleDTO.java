package uz.pdp.todo.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DatabaseRoleDTO {
    private String id;
    private String name;
    private String code;
    private String description;
}
