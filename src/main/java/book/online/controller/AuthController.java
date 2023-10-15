package book.online.controller;

import book.online.dto.user.UserLoginRequestDto;
import book.online.dto.user.UserLoginResponseDto;
import book.online.dto.user.UserRegistrationRequestDto;
import book.online.dto.user.UserRegistrationResponseDto;
import book.online.exception.RegistrationException;
import book.online.security.AuthenticationService;
import book.online.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Registration")
    public UserRegistrationResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Login")
    @ResponseStatus(HttpStatus.CREATED)
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
