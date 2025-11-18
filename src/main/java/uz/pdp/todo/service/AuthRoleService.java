package uz.pdp.todo.service;

import org.springframework.stereotype.Service;
import uz.pdp.todo.model.dto.AuthRoleChangeDto;
import uz.pdp.todo.model.dto.AuthRoleDto;
import uz.pdp.todo.model.entity.AuthRole;
import uz.pdp.todo.repository.AuthRoleRepository;
import uz.pdp.todo.validator.AuthRoleValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthRoleService
        implements CRUDService<AuthRoleDto, AuthRoleChangeDto, AuthRoleChangeDto, String> {

    private final AuthRoleRepository repository;
    private final AuthRoleValidator validator;

    public AuthRoleService(AuthRoleRepository repository, AuthRoleValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public AuthRoleDto create(AuthRoleChangeDto dto) {
        AuthRole role = new AuthRole();
        validator.validateOnCreate(dto);
        role.setName(dto.getName());
        role.setCode(dto.getCode());
        repository.save(role);

        return AuthRoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .code(role.getCode())
                .build();
    }

    @Override
    public AuthRoleDto update(String id, AuthRoleChangeDto dto) {
        AuthRole role = validator.existsAndGet(id);
        role.setName(dto.getName());
        role.setCode(dto.getCode());
        repository.save(role);
        return AuthRoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .code(role.getCode())
                .build();
    }

    @Override
    public AuthRoleDto get(String id) {
        AuthRole role = validator.existsAndGet(id);
        return AuthRoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .code(role.getCode())
                .build();
    }

    @Override
    public List<AuthRoleDto> getAll() {
        List<AuthRole> roles = repository.findAll();
        return roles.stream().map(
                role -> AuthRoleDto.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .code(role.getCode())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        AuthRole role = validator.existsAndGet(id);
        repository.delete(role);
    }
}
