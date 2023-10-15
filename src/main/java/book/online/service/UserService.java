package book.online.service;

import book.online.dto.user.UserRegistrationRequestDto;
import book.online.dto.user.UserRegistrationResponseDto;
import book.online.exception.RegistrationException;
import book.online.model.User;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException;

    Long getUserId();

    User getUser();
}
