package uz.pdp.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.todo.model.entity.AuthRole;

public interface AuthRoleRepository extends JpaRepository<AuthRole, String> {
}
