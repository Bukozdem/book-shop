package book.online.service;

import book.online.model.Role;
import book.online.model.Role.RoleName;

public interface RoleService {
    Role findRoleByName(RoleName roleName);
}
