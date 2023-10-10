package book.online.mapper;

import book.online.config.MapperConfig;
import book.online.dto.UserRegistrationRequestDto;
import book.online.dto.UserRegistrationResponseDto;
import book.online.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserRegistrationResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toModel(UserRegistrationRequestDto userRequestDto);
}
