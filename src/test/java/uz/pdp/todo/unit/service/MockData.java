package uz.pdp.todo.unit.service;

import uz.pdp.todo.model.Todo;
import uz.pdp.todo.model.TodoCreateDto;
import uz.pdp.todo.model.TodoDto;
import uz.pdp.todo.model.TodoUpdateDto;

public class MockData {
    public static Todo SUCCES_TODO = new Todo(1L, "Succes", "Succes description", false);
    public static TodoDto SUCCESS_TODO_D = new TodoDto(1L, "Succes", "Succes description", false);
    public static TodoCreateDto SUCCESS_TODO_C_D = new TodoCreateDto("Succes", "Succes description");
    public static TodoCreateDto TITLE_NULL_TODO_C_D = new TodoCreateDto(null, "Succes description");
    public static TodoCreateDto TITLE_4_TODO_C_D = new TodoCreateDto("Test", "Succes description");
    public static TodoUpdateDto SUCCESS_TODO_U_D = new TodoUpdateDto("Succes", "Succes description", true);


}
