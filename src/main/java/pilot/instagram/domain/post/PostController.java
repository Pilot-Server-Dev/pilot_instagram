package pilot.instagram.domain.post;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pilot.instagram.domain.post.dto.request.PostRequest;
import pilot.instagram.domain.post.dto.response.PostResponse;
import pilot.instagram.domain.post.service.PostService;
import pilot.instagram.global.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/new")
    public ApiResponse<PostResponse> createPost(@Valid @RequestBody PostRequest postRequest, HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        return ApiResponse.of(HttpStatus.CREATED, postService.savePost(postRequest, userId));
    }
}
