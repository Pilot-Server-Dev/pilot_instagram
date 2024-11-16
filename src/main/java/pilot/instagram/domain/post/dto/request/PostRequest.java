package pilot.instagram.domain.post.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostRequest {

    @NotNull(message = "게시글 내용은 필수로 입력해야 합니다.")
    private String content;
}
