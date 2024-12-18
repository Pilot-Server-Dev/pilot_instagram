package pilot.instagram.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pilot.instagram.domain.post.dto.request.PostRequest;
import pilot.instagram.domain.post.dto.response.PostPagingResponse;
import pilot.instagram.domain.post.dto.response.PostResponse;
import pilot.instagram.domain.post.entity.Post;
import pilot.instagram.domain.post.repository.PostRepository;
import pilot.instagram.domain.user.entity.User;
import pilot.instagram.domain.user.repository.UserRepository;
import pilot.instagram.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public PostResponse savePost(PostRequest postRequest, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUND.getMessage()));
        return PostResponse.of(postRepository.save(Post.fromDtoToEntity(postRequest, user)));
    }

    public PostResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.POST_NOT_FOUND.getMessage()));
        return PostResponse.of(post);
    }

    public Page<PostPagingResponse> getPosts(String userId, Pageable pageable) {
        return postRepository.findPostByUserIdWithPaged(userId, pageable);
    }
}
