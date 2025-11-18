package uz.pdp.todo.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.orm.jpa.vendor.Database;
import uz.pdp.todo.model.dto.UserDto;
import uz.pdp.todo.model.entity.ProjectDatabaseUser;

import java.util.List;

public interface DatabaseUserRepository extends CrudRepository<ProjectDatabaseUser, String> {


    @Query(value = """
            select du.id,
                   du.username,
                   du.password,
                   du.deleted,
                   du.version,
                   json_agg(dr.code) as roles
            from project_database_user du
                     left join project_database pd on du.database_id = pd.id
                     left join database_user_role ur on du.id = ur.database_user_id
                     left join database_role dr on dr.id = ur.database_role_id
            where du.version > ?2
              and pd.agent_id = ?1
            group by du.id, du.username, du.password, du.deleted, du.version
            """, nativeQuery = true)
    List<Object[]> getAllForAgent(String agentId, Long version);
}
