package pilot.instagram.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pilot.instagram.domain.user.repository.UserRepository;
import pilot.instagram.domain.user.request.UserRequest;
import pilot.instagram.domain.user.response.UserResponse;
import pilot.instagram.domain.user.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("신규 유저를 등록한다.")
    void saveUser() {
        //given
        UserRequest userRequest = UserRequest.builder().id("7l.tae").name("이기태").build();

        //when
        UserResponse userResponse = userService.saveUser(userRequest);

        //then
        assertThat(userResponse.getId()).isNotNull();
        assertThat(userResponse.getId()).isEqualTo("7l.tae");
        assertThat(userResponse.getName()).isEqualTo("이기태");
    }
}
