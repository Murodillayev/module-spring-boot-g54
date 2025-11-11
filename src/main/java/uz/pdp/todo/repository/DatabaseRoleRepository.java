package uz.pdp.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.todo.model.entity.DatabaseRole;

@Repository
public interface DatabaseRoleRepository extends JpaRepository<DatabaseRole, String> {
}
