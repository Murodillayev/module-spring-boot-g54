package uz.pdp.todo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.todo.model.entity.ProjectDatabase;

import java.util.List;
import java.util.Optional;

public interface ProjectDatabaseRepository extends JpaRepository<ProjectDatabase,String> {
    List<ProjectDatabase> findAllByDeletedFalse();

    @Query(value = "select * from database where name = :name and deleted =  false limit 1",
            nativeQuery = true)
    Optional<ProjectDatabase> findDbByName(@Param("name") String name);

    Optional<ProjectDatabase> findByAgentId(String agentId);
}
