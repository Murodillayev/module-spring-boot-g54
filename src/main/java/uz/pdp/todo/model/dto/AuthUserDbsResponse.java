package uz.pdp.todo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthUserDbsResponse {
    private String databaseName;
    private String username;
    private String password;
    private List<DatabaseRoleDTO> roleNames;
}
