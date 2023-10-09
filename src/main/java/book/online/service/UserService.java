package book.online.service;

import book.online.dto.UserRegistrationRequestDto;
import book.online.dto.UserRegistrationResponseDto;
import book.online.exception.RegistrationException;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException;
}
