package uz.pdp.todo.service;

import org.springframework.stereotype.Service;
import uz.pdp.todo.mapper.DatabaseRoleMapper;
import uz.pdp.todo.model.dto.DatabaseRoleCreateDTO;
import uz.pdp.todo.model.dto.DatabaseRoleDTO;
import uz.pdp.todo.model.entity.DatabaseRole;
import uz.pdp.todo.repository.DatabaseRoleRepository;
import uz.pdp.todo.validator.DatabaseRoleValidator;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DatabaseRoleService extends AbstractService<DatabaseRoleRepository, DatabaseRoleMapper, DatabaseRoleValidator>
        implements CRUDService<DatabaseRoleDTO, DatabaseRoleCreateDTO, DatabaseRoleDTO, String> {
    public DatabaseRoleService(DatabaseRoleRepository repository, DatabaseRoleMapper mapper, DatabaseRoleValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public DatabaseRoleDTO create(DatabaseRoleCreateDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntityOnCreate(dto)));
    }

    @Override
    public DatabaseRoleDTO update(String id, DatabaseRoleDTO dto) {
        DatabaseRole databaseRole = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Database role with id " + id + " not found"));
        databaseRole.setName(dto.getName());
        databaseRole.setDescription(dto.getDescription());
        databaseRole.setCode(dto.getCode());
        return mapper.toDto(repository.save(databaseRole));
    }

    @Override
    public DatabaseRoleDTO get(String id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new NoSuchElementException("Database role with id " + id + " not found."));
    }

    @Override
    public List<DatabaseRoleDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public void delete(String id) {
        DatabaseRole databaseRole = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Database role with id " + id + " not found."));
        databaseRole.setDeleted(true);
        repository.save(databaseRole);
    }
}
