package pilot.instagram.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import pilot.instagram.domain.association.PostTag;
import pilot.instagram.domain.comment.Comment;
import pilot.instagram.domain.like.Like;
import pilot.instagram.domain.media.Media;
import pilot.instagram.domain.post.entity.Post;
import pilot.instagram.domain.user.entity.User;

import java.util.List;

@Getter
public class PostResponse {
    private Long id;
    private String content;
    private String userId;
    private List<Comment> comments;
    private List<Like> likes;
    private List<Media> medias;
    private List<PostTag> postTags;

    @Builder
    private PostResponse(Long id, String content, User user, List<Comment> comments,
                         List<Like> likes, List<Media> medias, List<PostTag> postTags) {
        this.id = id;
        this.content = content;
        this.userId = user.getId();
        this.comments = comments;
        this.likes = likes;
        this.medias = medias;
        this.postTags = postTags;
    }

    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .user(post.getUser())
                .comments(post.getComments())
                .likes(post.getLikes())
                .medias(post.getMedias())
                .postTags(post.getPostTags())
                .build();
    }
}
