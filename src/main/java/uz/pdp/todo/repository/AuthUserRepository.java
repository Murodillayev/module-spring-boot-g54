package uz.pdp.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.todo.model.entity.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

    Optional<AuthUser> findByUsername(String username);

    Optional<AuthUser> findByIdAndDeleted(String id, Boolean deleted);
}
