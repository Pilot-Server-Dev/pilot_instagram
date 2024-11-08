package pilot.instagram.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pilot.instagram.global.ApiResponse;
import pilot.instagram.domain.user.request.UserRequest;
import pilot.instagram.domain.user.response.UserResponse;
import pilot.instagram.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/new")
    public ApiResponse<UserResponse> saveUser(@RequestBody UserRequest userRequest) {
        return ApiResponse.of(HttpStatus.CREATED, userService.saveUser(userRequest));
    }
}