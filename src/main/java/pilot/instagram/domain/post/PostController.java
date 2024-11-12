package pilot.instagram.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pilot.instagram.domain.post.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
}
