package uz.pdp.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data

public class TokenDto {
    private String token;
    private Date expiry;
}
