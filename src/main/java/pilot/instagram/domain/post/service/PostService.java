package pilot.instagram.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pilot.instagram.domain.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
}
