package uz.pdp.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.pdp.todo.mapper.ProjectAgentMapper;
import uz.pdp.todo.model.dto.agent.ProjectAgentCreateDTO;
import uz.pdp.todo.model.dto.agent.ProjectAgentDTO;
import uz.pdp.todo.model.dto.agent.ProjectAgentUpdateDTO;
import uz.pdp.todo.model.dto.database.ProjectDatabaseCreateDto;
import uz.pdp.todo.model.entity.ProjectAgent;
import uz.pdp.todo.repository.ProjectAgentRepository;
import uz.pdp.todo.validator.ProjectAgentValidator;

import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectAgentService extends AbstractService<ProjectAgentRepository, ProjectAgentMapper, ProjectAgentValidator>
        implements CRUDService<ProjectAgentDTO, ProjectAgentCreateDTO, ProjectAgentUpdateDTO, String> {

    private final ProjectDatabaseService projectDatabaseService;

    public ProjectAgentService(ProjectAgentRepository repository, ProjectAgentMapper mapper, ProjectAgentValidator validator, ProjectDatabaseService projectDatabaseService) {
        super(repository, mapper, validator);
        this.projectDatabaseService = projectDatabaseService;
    }

    @Override
    public ProjectAgentDTO create(ProjectAgentCreateDTO dto) {
        validator.validateOnCreate(dto);
        return mapper.toDto(repository.save(mapper.toEntityOnCreate(dto)));
    }

    @Override
    public ProjectAgentDTO update(String id, ProjectAgentUpdateDTO dto) {
        ProjectAgent projectAgent = validator.existsAndGet(id);
        projectAgent.setName(dto.getName());
        projectAgent.setDatabaseUsername(dto.getDatabaseUsername());
        projectAgent.setDatabaseUrl(dto.getDatabaseUrl());
        return mapper.toDto(repository.save(projectAgent));
    }

    @Override
    public ProjectAgentDTO get(String id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new NoSuchElementException("Project agent with id " + id + " not found."));
    }

    @Override
    public List<ProjectAgentDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public void delete(String id) {
        ProjectAgent projectAgent = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Project agent with id " + id + " not found."));
        projectAgent.setDeleted(true);
        repository.save(projectAgent);
    }

    public ProjectAgentDTO getAgentByDBUrl(String dbUrl) {
        String decodedUrl = URLDecoder.decode(dbUrl);
        Optional<ProjectAgent> byDatabaseUrl = repository.findByDatabaseUrl(decodedUrl);
        if (byDatabaseUrl.isPresent()) {
            return mapper.toDto(byDatabaseUrl.get());
        } else {
            throw new NoSuchElementException("Project agent with database url " + dbUrl + " not found.");
        }
    }

    @Transactional
    public ProjectAgentDTO createWithDb(ProjectAgentCreateDTO dto) {
        Optional<String> ifAlreadyExist = validator.checkIfAlreadyExist(dto);
        if (ifAlreadyExist.isPresent()) {
            return new ProjectAgentDTO(ifAlreadyExist.get(), dto.getName(), dto.getDatabaseUsername(), dto.getDatabasePassword(), dto.getDatabaseUrl());
        }
        ProjectAgentDTO projectAgentDTO = create(dto);
        projectDatabaseService.create(ProjectDatabaseCreateDto.builder()
                .agentId(projectAgentDTO.getId())
                .name(projectAgentDTO.getName())
                .membersId(Collections.emptyList())
                .build());
        return projectAgentDTO;
    }
}
