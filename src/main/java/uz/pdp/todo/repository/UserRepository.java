package uz.pdp.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.todo.model.AuthUser;

public interface UserRepository extends JpaRepository<AuthUser, String> {
}
