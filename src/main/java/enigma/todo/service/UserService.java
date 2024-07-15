package enigma.todo.service;

import enigma.todo.dto.RegisterDTO;
import enigma.todo.model.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Map<String, String> register(RegisterDTO registerDTO);

    Map<String, String> login(String username, String password);

    User findByUsername(String username);

    // TODO upgrade functionality
    User updateById(Long id, User updatedUser);

    // TODO upgrade functionality
    void deleteById(Long id);
}
