package uz.pdp.todo.unit.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uz.pdp.todo.model.TodoCreateDto;
import uz.pdp.todo.resource.TodoResource;
import uz.pdp.todo.service.TodoService;
import uz.pdp.todo.unit.service.MockData;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoResource.class)
class TodoResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private TodoService service;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createTodo() throws Exception {

        Mockito.when(service.create(any(TodoCreateDto.class)))
                .thenReturn(MockData.SUCCESS_TODO_D);

        mockMvc.perform(
                        post("/api/v1/todo")
                                .content(objectMapper.writeValueAsString(MockData.SUCCESS_TODO_C_D))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect((jsonPath("$.id").value(Matchers.notNullValue())));


    }
}