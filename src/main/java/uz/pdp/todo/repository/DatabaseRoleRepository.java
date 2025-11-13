package uz.pdp.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.todo.model.entity.DatabaseRole;

import java.util.Collection;
import java.util.List;

@Repository
public interface DatabaseRoleRepository extends JpaRepository<DatabaseRole, String> {

    List<DatabaseRole> findAllByIdIn(Collection<String> ids);
}
