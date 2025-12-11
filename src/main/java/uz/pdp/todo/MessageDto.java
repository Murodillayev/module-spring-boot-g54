package uz.pdp.todo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDto {
    private String message;
    private String sender;
    private LocalDateTime sendTime;
}
