package uz.pdp.todo.repository;

import uz.pdp.todo.model.entity.ProjectDatabaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectDatabaseUserRepository extends JpaRepository<ProjectDatabaseUser, String> {
    List<ProjectDatabaseUser> findDatabaseUserByDeletedFalse();

    @Query(value = "SELECT * FROM database_user WHERE id IN (:ids) AND deleted = false",
            nativeQuery = true)
    List<ProjectDatabaseUser> findAllByIdIn(@Param("ids") List<Long> membersId);

}
