package pilot.instagram.domain.comment;

import jakarta.persistence.*;
import lombok.Getter;
import pilot.instagram.domain.common.BaseTimeEntity;
import pilot.instagram.domain.post.Post;
import pilot.instagram.domain.user.User;

@Entity @Getter
public class Comment extends BaseTimeEntity {

    @Column(name = "comment_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
