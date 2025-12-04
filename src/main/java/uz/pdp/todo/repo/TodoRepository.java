package uz.pdp.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
