package uz.pdp.todo.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class TokenDto {
    private String token;
    private Date expiry;
}
