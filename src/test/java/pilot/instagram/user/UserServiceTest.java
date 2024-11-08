package pilot.instagram.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pilot.instagram.domain.user.repository.UserRepository;
import pilot.instagram.domain.user.request.UserRequest;
import pilot.instagram.domain.user.response.UserResponse;
import pilot.instagram.domain.user.service.UserService;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        String id = UUID.randomUUID().toString();
        UserRequest userRequest = UserRequest.builder().id(id).name("이기태").build();

        //when
        UserResponse userResponse = userService.saveUser(userRequest);

        //then
        assertThat(userResponse.getId()).isNotNull();
        assertThat(userResponse.getId()).isEqualTo(id);
        assertThat(userResponse.getName()).isEqualTo("이기태");
    }

    @Test
    @DisplayName("신규 유저는 중복된 아이디를 등록할 수 없다.")
    void saveDuplicateUser() {
        //given
        String id = UUID.randomUUID().toString();
        UserRequest userRequest = UserRequest.builder().id(id).name("이기태").build();

        //when //then
        userService.saveUser(userRequest);
        assertThatThrownBy(() -> userService.saveUser(userRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("DUPLICATE_ID");
    }
}
