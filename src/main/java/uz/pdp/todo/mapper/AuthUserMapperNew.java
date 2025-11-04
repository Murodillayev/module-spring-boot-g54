package uz.pdp.todo.mapper;


import org.mapstruct.*;
import uz.pdp.todo.model.dto.AuthUserCreateDto;
import uz.pdp.todo.model.dto.AuthUserDto;
import uz.pdp.todo.model.dto.AuthUserUpdateDto;
import uz.pdp.todo.model.entity.AuthUser;

import java.util.List;

@Mapper
public abstract class AuthUserMapperNew {

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phone", source = "userPhone")
    public abstract AuthUser fromDto(AuthUserCreateDto dto);

    @Mapping(target = "username", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "phone", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void fromDto(AuthUserUpdateDto dto, @MappingTarget AuthUser user);

    public abstract List<AuthUserDto> toDto(List<AuthUser> authUsers);

    public abstract AuthUserDto toDto(AuthUser authUser);
}





// Object /api/{path} {method= reqbody}

// sql="select * from where id=%id" product
