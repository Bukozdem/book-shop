package book.online.service;

import book.online.dto.user.UserRegistrationRequestDto;
import book.online.dto.user.UserRegistrationResponseDto;
import book.online.exception.RegistrationException;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException;

    Long getUserId();
}
