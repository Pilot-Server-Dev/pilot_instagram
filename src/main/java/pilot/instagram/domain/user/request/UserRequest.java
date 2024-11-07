package pilot.instagram.domain.user.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NonNull
    private String id;
    private String name;
}
