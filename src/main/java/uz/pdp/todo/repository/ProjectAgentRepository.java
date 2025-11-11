package uz.pdp.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.todo.model.entity.ProjectAgent;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectAgentRepository extends JpaRepository<ProjectAgent, String> {
    Optional<ProjectAgent> findByDatabaseUrl(String databaseUrl);
}
