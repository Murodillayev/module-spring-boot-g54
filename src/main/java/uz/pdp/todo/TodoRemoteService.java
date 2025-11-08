package uz.pdp.todo;


import java.util.List;

public interface TodoRemoteService {

    List<Todo> getTodos();

    Todo getTodo(Integer id);

    Todo createTodo(Todo todo);

    Todo updateTodo(String title, Integer id);

    Todo completeTodo(Integer id);

    void deleteTodo(Integer id);
}
