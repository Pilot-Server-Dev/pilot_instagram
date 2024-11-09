package pilot.instagram.domain.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotNull(message = "유저 아이디는 필수로 입력해야 합니다.")
    private String id;
    @NotNull(message = "유저 이름은 필수로 입력해야 합니다.")
    private String name;
}
