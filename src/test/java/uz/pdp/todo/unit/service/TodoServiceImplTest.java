package uz.pdp.todo.unit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.pdp.todo.BadRequestException;
import uz.pdp.todo.mapper.TodoMapper;
import uz.pdp.todo.model.Todo;
import uz.pdp.todo.model.TodoCreateDto;
import uz.pdp.todo.model.TodoDto;
import uz.pdp.todo.repo.TodoRepository;
import uz.pdp.todo.service.TodoServiceImpl;
import uz.pdp.todo.utils.ErrorMessages;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {

    @Mock
    private TodoMapper mapper;

    @Mock
    private TodoRepository repository;


    @InjectMocks
    private TodoServiceImpl service;


//    @BeforeEach
//    void setUp() {
//        mapper = Mockito.mock(TodoMapper.class);
//        repository = Mockito.mock(TodoRepository.class);
//        service = new TodoServiceImpl(repository, mapper);
//    }

    @Test
    @DisplayName(value = "Ma'lumtlar valid bolsa todo qaytarishi kerak")
    void create1() {
        TodoCreateDto dto = MockData.SUCCESS_TODO_C_D;
        TodoDto todoInfo = MockData.SUCCESS_TODO_D;
        Todo todo = MockData.SUCCES_TODO;


        Mockito.when(mapper.fromDto(dto)).thenReturn(todo);
        Mockito.when(repository.save(todo)).thenReturn(todo);
        Mockito.when(mapper.toDto(todo)).thenReturn(todoInfo);

        TodoDto result = service.create(dto);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertNotNull(result.getTitle());
        Assertions.assertNotNull(result.getDescription());
        Assertions.assertFalse(result.isCompleted());

        Mockito.verify(mapper, Mockito.times(1)).fromDto(dto);
        Mockito.verify(mapper, Mockito.times(1)).toDto(todo);
        Mockito.verify(repository, Mockito.times(1)).save(todo);
    }

    @Test
    @DisplayName(value = "Agar title null bolsa error berishi kerak")
    void create2() {
        TodoCreateDto dto = MockData.TITLE_NULL_TODO_C_D;
        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.create(dto));
        Assertions.assertEquals(ErrorMessages.TITLE_IS_REQUIRED, exception.getMessage());
    }

    @Test
    @DisplayName(value = "Agar title uzunligi 5 dan kichik bolsa error sodir bolishi kerak")
    void create3() {
        TodoCreateDto dto = MockData.TITLE_4_TODO_C_D;
        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.create(dto));
        Assertions.assertEquals(ErrorMessages.TITLE_IS_TOO_SHORT, exception.getMessage());
    }


}