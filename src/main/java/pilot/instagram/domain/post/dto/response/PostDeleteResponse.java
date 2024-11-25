package pilot.instagram.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import pilot.instagram.domain.post.entity.Post;

@Getter
public class PostDeleteResponse {
    private Long id;

    @Builder
    private PostDeleteResponse(Long id) {
        this.id = id;
    }

    public static PostDeleteResponse of(Post post) {
        return PostDeleteResponse.builder()
                .id(post.getId())
                .build();
    }
}
