package uz.pdp.todo.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class AppErrorDto {
    private String message;
    private String developerMessage;
    private Integer status;
    private Date timestamp;
    private String path;
}
