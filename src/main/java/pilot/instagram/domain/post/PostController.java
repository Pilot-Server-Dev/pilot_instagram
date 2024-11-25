package pilot.instagram.domain.post;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pilot.instagram.domain.post.dto.request.PostRequest;
import pilot.instagram.domain.post.dto.response.PostDeleteResponse;
import pilot.instagram.domain.post.dto.response.PostPagingResponse;
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

    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> getPost(@PathVariable("postId") Long postId) {
        return ApiResponse.of(HttpStatus.OK, postService.getPost(postId));
    }

    @GetMapping("/myPostList")
    public ApiResponse<Page<PostPagingResponse>> getPosts(HttpSession session, Pageable pageable) {
        String userId = session.getAttribute("userId").toString();
        return ApiResponse.of(HttpStatus.OK, postService.getPosts(userId, pageable));
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<PostDeleteResponse> deletePost(@PathVariable("postId") Long postId, HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        return ApiResponse.of(HttpStatus.OK, postService.deletePost(postId, userId));
    }
}
