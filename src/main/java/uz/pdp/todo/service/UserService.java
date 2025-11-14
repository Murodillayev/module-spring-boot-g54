package uz.pdp.todo.service;

import org.springframework.stereotype.Service;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.mapper.AuthUserMapper;
import uz.pdp.todo.model.dto.authUser.AuthUserDto;
import uz.pdp.todo.model.dto.authUser.AuthUserCreateDto;
import uz.pdp.todo.model.dto.authUser.AuthUserUpdateDto;
import uz.pdp.todo.repository.AuthUserRepository;
import uz.pdp.todo.validator.AuthUserValidator;

import java.util.List;

@Service
public class UserService
        extends AbstractService<
        AuthUserRepository,
        AuthUserMapper,
        AuthUserValidator>
        implements CRUDService<AuthUserDto, AuthUserCreateDto, AuthUserUpdateDto, String> {

    public UserService(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public AuthUserDto create(AuthUserCreateDto dto) {
        validator.validateOnCreate(dto);
        AuthUser authUser = mapper.fromDto(dto);
        return mapper.toDto(repository.save(authUser));
    }

    @Override
    public AuthUserDto update(String id, AuthUserUpdateDto dto) {
        AuthUser authUser = validator.existsAndGet(id);
        mapper.fromDto(dto, authUser);
        return mapper.toDto(repository.save(authUser));
    }

    @Override
    public AuthUserDto get(String id) {
        AuthUser authUser = validator.existsAndGet(id);
        return mapper.toDto(authUser);
    }

    @Override
    public List<AuthUserDto> getAll() {
        List<AuthUser> all = repository.findAll();
        return mapper.toDto(all);
    }

    @Override
    public void delete(String id) {
        AuthUser authUser = validator.existsAndGet(id);
        authUser.setDeleted(true);
        repository.save(authUser);
    }

    public AuthUserDto updateIgnoreNull(String id, AuthUserUpdateDto dto) {
        AuthUser authUser = validator.existsAndGet(id);
        mapper.fromDtoIgnoreNull(dto, authUser);
        return mapper.toDto(repository.save(authUser));
    }
}
