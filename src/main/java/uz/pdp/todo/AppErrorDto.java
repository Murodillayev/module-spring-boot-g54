package uz.pdp.todo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

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
