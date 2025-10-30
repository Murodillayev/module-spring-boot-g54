package uz.pdp.todo.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private Date expiry;
    private String refreshToken;
    private Date refreshExpiry;
}
