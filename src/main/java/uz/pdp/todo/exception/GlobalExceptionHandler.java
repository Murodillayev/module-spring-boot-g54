package uz.pdp.todo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.pdp.todo.model.dto.AppErrorDto;

import java.util.Arrays;
import java.util.Date;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<AppErrorDto> error_404(NotFoundException e, HttpServletRequest request) {
        AppErrorDto errorDto = AppErrorDto.builder()
                .message(e.getMessage())
                .developerMessage(Arrays.toString(e.getStackTrace()))
                .status(404)
                .timestamp(new Date())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
