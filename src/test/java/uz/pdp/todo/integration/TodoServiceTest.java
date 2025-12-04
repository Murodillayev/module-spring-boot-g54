package uz.pdp.todo.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uz.pdp.todo.BadRequestException;
import uz.pdp.todo.model.TodoCreateDto;
import uz.pdp.todo.model.TodoDto;
import uz.pdp.todo.service.TodoService;
import uz.pdp.todo.unit.service.MockData;
import uz.pdp.todo.utils.ErrorMessages;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoService service;

    @Test
    @DisplayName(value = "Ma'lumtlar valid bolsa todo qaytarishi kerak")
    void create1() {
        TodoCreateDto dto = MockData.SUCCESS_TODO_C_D;


        TodoDto result = service.create(dto);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertNotNull(result.getTitle());
        Assertions.assertNotNull(result.getDescription());
        Assertions.assertFalse(result.isCompleted());
    }

    @Test
    @DisplayName(value = "Agar title null bolsa error berishi kerak")
    void create2() {
        TodoCreateDto dto = MockData.TITLE_NULL_TODO_C_D;
        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.create(dto));
        Assertions.assertEquals(ErrorMessages.TITLE_IS_REQUIRED, exception.getMessage());
    }
}
