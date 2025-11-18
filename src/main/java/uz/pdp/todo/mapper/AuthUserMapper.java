package uz.pdp.todo.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.authUser.AuthUserCreateDto;
import uz.pdp.todo.model.dto.authUser.AuthUserDto;
import uz.pdp.todo.model.dto.authUser.AuthUserUpdateDto;
import uz.pdp.todo.model.dto.IdNameDto;
import uz.pdp.todo.model.entity.AuthRole;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.service.PasswordGenerator;
import uz.pdp.todo.validator.AuthRoleValidator;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthUserMapper implements BaseMapper {
    private final AuthRoleValidator authRoleValidator;
    private final PasswordEncoder passwordEncoder;
    private final PasswordGenerator passwordGenerator;

    public AuthUser fromDto(AuthUserCreateDto dto) {
        AuthRole role = authRoleValidator.existsAndGet(dto.getRoleId());
        AuthUser authUser = new AuthUser();
        authUser.setEmail(dto.getEmail());
        authUser.setUsername(dto.getUsername());
        authUser.setPassword(passwordEncoder.encode(passwordGenerator.generatePassword()));
        authUser.setName(dto.getName());
        authUser.setPhone(dto.getPhone());
        authUser.setRole(role);
        return authUser;
    }

    public void fromDto(AuthUserUpdateDto dto, AuthUser authUser) {
        AuthRole role = authRoleValidator.existsAndGet(dto.getRoleId());
        authUser.setEmail(dto.getEmail());
        authUser.setUsername(dto.getUsername());
        authUser.setName(dto.getName());
        authUser.setPhone(dto.getPhone());
        authUser.setRole(role);
    }

    public void fromDtoIgnoreNull(AuthUserUpdateDto dto, AuthUser authUser) {
        if (dto.getEmail() != null) {
            authUser.setEmail(dto.getEmail());
        }
        if (dto.getUsername() != null) {
            authUser.setUsername(dto.getUsername());
        }
        if (dto.getName() != null) {
            authUser.setName(dto.getName());
        }
        if (dto.getPhone() != null) {
            authUser.setPhone(dto.getPhone());
        }
        if (dto.getRoleId() != null) {
            authUser.setRole(authRoleValidator.existsAndGet(dto.getRoleId()));
        }
    }
    public AuthUserDto toDto(AuthUser authUser) {
        AuthRole authRole = authUser.getRole();
        IdNameDto role = authRole == null ? null : IdNameDto.builder()
                .id(authRole.getId())
                .name(authRole.getName())
                .build();

        return AuthUserDto.builder()
                .username(authUser.getUsername())
                .id(authUser.getId())
                .email(authUser.getEmail())
                .name(authUser.getName())
                .phone(authUser.getPhone())
                .role(role)
                .build();
    }

    public List<AuthUserDto> toDto(List<AuthUser> authUsers) {
        return authUsers.stream().map(this::toDto).collect(Collectors.toList());
    }


}
