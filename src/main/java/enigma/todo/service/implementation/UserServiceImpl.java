package enigma.todo.service.implementation;

import enigma.todo.dto.RegisterDTO;
import enigma.todo.model.Role;
import enigma.todo.model.User;
import enigma.todo.repository.UserRepository;
import enigma.todo.security.JwtUtils;
import enigma.todo.service.RoleService;
import enigma.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public Map<String, String> register(RegisterDTO registerDTO) {
        List<Role> roles = roleService.findByNameIn(registerDTO.getRoles());

        userRepository.save(User.builder()
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .roles(roles)
                .build()
        );

        String token = jwtUtils.generateToken(registerDTO.getUsername());
        return Map.of("authToken", token);
    }

    @Override
    public Map<String, String> login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = jwtUtils.generateToken(username);

        return Map.of("authToken", token);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateById(Long id, User updatedUser) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        foundUser.setUsername(updatedUser.getUsername());
        foundUser.setEmail(updatedUser.getEmail());
        foundUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

        return userRepository.save(foundUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
