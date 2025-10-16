package uz.pdp.todo.dao;


import uz.pdp.todo.model.Todo;

import java.util.List;
import java.util.Optional;


public interface TodoDao {

    void deleteById(String id);

    List<Todo> findAll();

    Optional<Todo> findById(String id);

    Todo save(Todo todo);


}
