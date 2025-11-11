package uz.pdp.todo.service;

import org.springframework.stereotype.Service;
import uz.pdp.todo.mapper.ProjectAgentMapper;
import uz.pdp.todo.model.dto.ProjectAgentCreateDTO;
import uz.pdp.todo.model.dto.ProjectAgentResponseDTO;
import uz.pdp.todo.model.dto.ProjectAgentUpdateDTO;
import uz.pdp.todo.model.entity.ProjectAgent;
import uz.pdp.todo.repository.ProjectAgentRepository;
import uz.pdp.todo.validator.ProjectAgentValidator;

import java.net.URLDecoder;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectAgentService extends AbstractService<ProjectAgentRepository, ProjectAgentMapper, ProjectAgentValidator>
        implements CRUDService<ProjectAgentResponseDTO, ProjectAgentCreateDTO, ProjectAgentUpdateDTO, String> {

    public ProjectAgentService(ProjectAgentRepository repository, ProjectAgentMapper mapper, ProjectAgentValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public ProjectAgentResponseDTO create(ProjectAgentCreateDTO dto) {
        validator.validateOnCreate(dto);
        return mapper.toDto(repository.save(mapper.toEntityOnCreate(dto)));
    }

    @Override
    public ProjectAgentResponseDTO update(String id, ProjectAgentUpdateDTO dto) {
        ProjectAgent projectAgent = validator.existsAndGet(id);
        projectAgent.setName(dto.getName());
        projectAgent.setDatabaseUsername(dto.getDatabaseUsername());
        projectAgent.setDatabaseUrl(dto.getDatabaseUrl());
        return mapper.toDto(repository.save(projectAgent));
    }

    @Override
    public ProjectAgentResponseDTO get(String id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new NoSuchElementException("Project agent with id " + id + " not found."));
    }

    @Override
    public List<ProjectAgentResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public void delete(String id) {
        ProjectAgent projectAgent = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Project agent with id " + id + " not found."));
        projectAgent.setDeleted(true);
        repository.save(projectAgent);
    }

    public ProjectAgentResponseDTO getAgentByDBUrl(String dbUrl) {
        String decodedUrl = URLDecoder.decode(dbUrl);
        Optional<ProjectAgent> byDatabaseUrl = repository.findByDatabaseUrl(decodedUrl);
        if (byDatabaseUrl.isPresent()) {
            return mapper.toDto(byDatabaseUrl.get());
        } else {
            throw new NoSuchElementException("Project agent with database url " + dbUrl + " not found.");
        }
    }
}
