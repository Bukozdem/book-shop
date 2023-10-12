package book.online.service.impl;

import book.online.model.Role;
import book.online.model.Role.RoleName;
import book.online.repository.role.RoleRepository;
import book.online.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role findRoleByName(RoleName roleName) {
        return repository.findRoleByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException(String
                .join("Couldn't find role ", roleName.name())));
    }
}
