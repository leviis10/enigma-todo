package enigma.todo.service;

import enigma.todo.model.Role;
import enigma.todo.model.UserRole;

import java.util.List;

public interface RoleService {
    List<Role> findByNameIn(List<UserRole> roles);
}
