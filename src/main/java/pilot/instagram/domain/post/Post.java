package pilot.instagram.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import pilot.instagram.domain.association.PostTag;
import pilot.instagram.domain.comment.Comment;
import pilot.instagram.domain.common.BaseTimeEntity;
import pilot.instagram.domain.like.Like;
import pilot.instagram.domain.media.Media;
import pilot.instagram.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post extends BaseTimeEntity {

    @Column(name = "post_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
