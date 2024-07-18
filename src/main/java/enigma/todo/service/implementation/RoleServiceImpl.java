package enigma.todo.service.implementation;

import enigma.todo.model.Role;
import enigma.todo.model.UserRole;
import enigma.todo.repository.RoleRepository;
import enigma.todo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> findByNameIn(List<UserRole> roles) {
        return roleRepository.findByNameIn(roles);
    }
}
