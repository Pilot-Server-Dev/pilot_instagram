package pilot.instagram.domain.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pilot.instagram.global.ApiResponse;
import pilot.instagram.domain.user.request.UserRequest;
import pilot.instagram.domain.user.response.UserResponse;
import pilot.instagram.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public ApiResponse<UserResponse> saveUser(@Valid @RequestBody UserRequest userRequest) {
        return ApiResponse.of(HttpStatus.CREATED, userService.saveUser(userRequest));
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody UserRequest userRequest, HttpSession session) {
        session.setAttribute("user", userRequest.getId());
        return ApiResponse.of(HttpStatus.OK, session.getId());
    }

    @GetMapping("/test")
    public String test(HttpSession httpSession) {
        return httpSession.getAttribute("user").toString();
    }
}