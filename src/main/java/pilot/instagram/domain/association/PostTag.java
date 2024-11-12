package pilot.instagram.domain.association;

import jakarta.persistence.*;
import lombok.Getter;
import pilot.instagram.domain.post.entity.Post;
import pilot.instagram.domain.tag.Tag;

@Entity @Getter
public class PostTag {

    @Column(name = "post_tag_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
