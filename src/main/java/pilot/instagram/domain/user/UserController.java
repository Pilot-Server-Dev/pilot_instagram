package pilot.instagram.domain.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pilot.instagram.global.ApiResponse;
import pilot.instagram.domain.user.dto.request.UserRequest;
import pilot.instagram.domain.user.dto.response.UserResponse;
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
    public ApiResponse<UserResponse> login(@Valid @RequestBody UserRequest userRequest, HttpSession session) {
        UserResponse userResponse = userService.login(userRequest.getId());
        session.setAttribute("userId", userRequest.getId());
        // session.getAttribute("userId") 로 userId를 세션에서 가져올 수 있음
        return ApiResponse.of(HttpStatus.OK, userResponse);
    }
}