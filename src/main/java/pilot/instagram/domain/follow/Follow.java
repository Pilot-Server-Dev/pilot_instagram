package pilot.instagram.domain.follow;

import jakarta.persistence.*;
import lombok.Getter;
import pilot.instagram.domain.common.BaseTimeEntity;
import pilot.instagram.domain.user.domain.User;

@Entity @Getter
public class Follow extends BaseTimeEntity {

    @Column(name = "follow_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;
}
