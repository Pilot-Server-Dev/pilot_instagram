package pilot.instagram.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pilot.instagram.domain.post.dto.response.PostPagingResponse;

public interface PostCustomRepository {
    Page<PostPagingResponse> findPostByUserIdWithPaged(String userId, Pageable pageable);
}
