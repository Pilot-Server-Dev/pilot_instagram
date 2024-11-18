package pilot.instagram.domain.post.dto.response;

import lombok.Getter;

@Getter
public class PostPagingResponse {
    private Long id;
    private String content;
    private String userId;
}
