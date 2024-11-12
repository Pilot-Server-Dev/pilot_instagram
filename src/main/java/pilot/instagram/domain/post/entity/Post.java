package pilot.instagram.domain.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pilot.instagram.domain.association.PostTag;
import pilot.instagram.domain.comment.Comment;
import pilot.instagram.domain.common.BaseTimeEntity;
import pilot.instagram.domain.like.Like;
import pilot.instagram.domain.media.Media;
import pilot.instagram.domain.post.dto.request.PostRequest;
import pilot.instagram.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Media> medias = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostTag> postTags = new ArrayList<>();

    @Builder
    private Post(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public static Post fromDtoToEntity(PostRequest postRequest, User user) {
        return Post.builder()
                .content(postRequest.getContent())
                .user(user)
                .build();
    }
}
