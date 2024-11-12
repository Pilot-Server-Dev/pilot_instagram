package pilot.instagram.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pilot.instagram.domain.user.entity.User;
import pilot.instagram.domain.user.repository.UserRepository;
import pilot.instagram.domain.user.dto.request.UserRequest;
import pilot.instagram.domain.user.dto.response.UserResponse;
import pilot.instagram.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        validateDuplicateId(userRequest.getId());
        return UserResponse.of(userRepository.save(User.fromDtoToEntity(userRequest)));
    }

    public UserResponse login(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUND.getMessage()));
        return UserResponse.of(user);
    }

    private void validateDuplicateId(String id) {
        userRepository.findById(id).ifPresent(user -> {throw new IllegalArgumentException(ErrorCode.DUPLICATED_ID.getMessage());});
    }
}
