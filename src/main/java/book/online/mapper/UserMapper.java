package book.online.mapper;

import book.online.config.MapperConfig;
import book.online.dto.UserRegistrationRequestDto;
import book.online.dto.UserRegistrationResponseDto;
import book.online.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserRegistrationResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userRequestDto);
}
