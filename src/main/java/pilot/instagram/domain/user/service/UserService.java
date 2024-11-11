package pilot.instagram.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pilot.instagram.domain.user.entity.User;
import pilot.instagram.domain.user.repository.UserRepository;
import pilot.instagram.domain.user.request.UserRequest;
import pilot.instagram.domain.user.response.UserResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        validateDuplicateId(userRequest.getId());
        return UserResponse.of(userRepository.save(User.fromDtoToUser(userRequest)));
    }

    public UserResponse login(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("아이디를 찾을 수 없습니다"));
        return UserResponse.of(user);
    }

    private void validateDuplicateId(String id) {
        userRepository.findById(id).ifPresent(user -> {throw new IllegalArgumentException("중복된 아이디입니다.");});
    }
}
