package uz.pdp.todo.repository;

import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.model.entity.ProjectDatabaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectDatabaseUserRepository extends JpaRepository<ProjectDatabaseUser, String> {

    List<ProjectDatabaseUser> findDatabaseUserByDeletedFalse();

    @Query(value = "select * from project_database where id in (select project_database_id from project_database_members where members_id in (select id from project_database_user where user_id = :memberId and deleted = false))", nativeQuery = true)
    List<ProjectDatabase> findAllByMemberId(@Param("memberId") String memberId);

    Optional<ProjectDatabaseUser> findByIdAndDeleted(String id, Boolean deleted);
}
