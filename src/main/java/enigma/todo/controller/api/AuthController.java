package enigma.todo.controller.api;

import enigma.todo.dto.LoginDTO;
import enigma.todo.dto.RegisterDTO;
import enigma.todo.dto.response.Response;
import enigma.todo.dto.response.SuccessResponse;
import enigma.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<Map<String, String>>> register(
            @Valid @RequestBody RegisterDTO registerDTO
    ) {
        return Response.success(
                userService.register(registerDTO),
                "User registered successfully",
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<Map<String, String>>> login(
            @Valid @RequestBody LoginDTO loginDTO
    ) {
        return Response.success(
                userService.login(loginDTO.getUsername(), loginDTO.getPassword()),
                "User logged in successfully",
                HttpStatus.OK
        );
    }
}
