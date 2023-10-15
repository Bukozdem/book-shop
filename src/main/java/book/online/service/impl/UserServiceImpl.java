package book.online.service.impl;

import book.online.dto.user.UserRegistrationRequestDto;
import book.online.dto.user.UserRegistrationResponseDto;
import book.online.exception.EntityNotFoundException;
import book.online.exception.RegistrationException;
import book.online.mapper.UserMapper;
import book.online.model.Role.RoleName;
import book.online.model.ShoppingCart;
import book.online.model.User;
import book.online.repository.cart.ShoppingCartRepository;
import book.online.repository.user.UserRepository;
import book.online.service.RoleService;
import book.online.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        User user = userMapper.toModel(request);
        user.setRoles(Set.of(roleService.findRoleByName(RoleName.ROLE_USER)));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        shoppingCartRepository.save(new ShoppingCart(savedUser));
        return userMapper.toDto(savedUser);
    }

    @Override
    public Long getUserId() {
        return getUser().getId();
    }

    @Override
    public User getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .join("Couldn't find user by email", email)));
    }
}
