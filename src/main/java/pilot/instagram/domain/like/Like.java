package pilot.instagram.domain.like;

import jakarta.persistence.*;
import lombok.Getter;
import pilot.instagram.domain.common.BaseTimeEntity;
import pilot.instagram.domain.post.Post;
import pilot.instagram.domain.user.User;

@Entity
@Getter
@Table(name = "Likes")
public class Like extends BaseTimeEntity {

    @Column(name = "like_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
