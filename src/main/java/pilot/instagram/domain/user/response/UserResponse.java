package pilot.instagram.domain.user.response;

import lombok.Builder;
import lombok.Getter;
import pilot.instagram.domain.user.entity.User;

@Getter
public class UserResponse {
    private String id;
    private String name;

    @Builder
    private UserResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
