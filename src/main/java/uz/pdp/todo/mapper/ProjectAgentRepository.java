package uz.pdp.todo.mapper;

import org.springframework.data.repository.Repository;
import uz.pdp.todo.model.entity.ProjectAgent;

interface ProjectAgentRepository extends Repository<ProjectAgent, String> {
    ProjectAgent findById(String id);
}
