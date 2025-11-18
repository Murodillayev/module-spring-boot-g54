package uz.pdp.todo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.model.entity.ProjectDatabaseUser;

import java.util.List;
import java.util.Optional;

public interface ProjectDatabaseRepository extends JpaRepository<ProjectDatabase, String> {
    List<ProjectDatabase> findAllByDeletedFalse();

    @Query(value = "select * from project_database where name = :name and deleted =  false limit 1",
            nativeQuery = true)
    Optional<ProjectDatabase> findDbByName(@Param("name") String name);

    Optional<ProjectDatabase> findByAgentId(String agentId);

    @Query(value = "SELECT * FROM database_user WHERE id IN (:ids) AND deleted = false",
            nativeQuery = true)
    List<ProjectDatabaseUser> findAllByIdIn(@Param("ids") List<Long> membersId);

    Optional<ProjectDatabase> findByIdAndDeleted(String id, Boolean deleted);
}
