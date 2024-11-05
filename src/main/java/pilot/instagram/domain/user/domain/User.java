package pilot.instagram.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pilot.instagram.domain.comment.Comment;
import pilot.instagram.domain.common.BaseTimeEntity;
import pilot.instagram.domain.follow.Follow;
import pilot.instagram.domain.like.Like;
import pilot.instagram.domain.post.Post;
import pilot.instagram.domain.user.request.UserRequest;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Column(name = "user_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "follower")
    private List<Follow> followers;

    @OneToMany(mappedBy = "following")
    private List<Follow> followings;

    @Builder
    private User(String name) {
        this.name = name;
    }

    public static User fromDtoToUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .build();
    }
}
