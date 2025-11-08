package uz.pdp.todo;


import java.util.List;


public interface TodoRemoteService {

    List<Todo> getTodos();

    Todo createTodo(Todo todo);

    default Todo getTodo(Integer id) {
        return null;
    }

    default Todo updateTodo(String title, Integer id) {
        return null;
    }

    default Todo completeTodo(Integer id) {
        return null;
    }

    default void deleteTodo(Integer id) {

    }
}
